package io.bloco.biblioteca

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import io.bloco.biblioteca.helpers.Helpers
import io.bloco.biblioteca.helpers.Helpers.dateToStringDatePicker
import io.bloco.biblioteca.helpers.Validation
import io.bloco.biblioteca.helpers.ValidationErrors
import kotlinx.android.synthetic.main.activity_add_book.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddBookActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val datePicker by lazy { initializeDatePicker() }
    private val bookRepository by lazy { (application as App).getBookRepository() }
    private var currentPhotoPath: String? = null
    private var bookPhotoPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        etDate.setOnClickListener { datePicker.show() }
        btnTakePhoto.setOnClickListener { dispatchTakePictureIntent() }
        btnUploadPhoto.setOnClickListener {

            // Create intent to select photo from the gallery
            val intentOpenGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // Start the intent with a request code
            startActivityForResult(intentOpenGallery, REQUEST_SELECT_PHOTO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int? = item?.itemId
        if (id == R.id.itemSaveBook) {

            val newBook = Book(
                title = etTitle.text.toString(),
                author = etAuthor.text.toString(),
                publishDate = Helpers.stringToDate(etDate.text.toString()),
                isbn = etIsbn.text.toString(),
                read = cbRead.isChecked,
                uriCover = bookPhotoPath
            )

            val errorList = Validation().validateBook(newBook)
            if (errorList.isEmpty()) {
                bookRepository.addBook(newBook) { returnToMain() }
                return true
            } else {
                inLayoutTitle.isErrorEnabled = false
                inLayoutIsbn.isErrorEnabled = false
                inLayoutDate.isErrorEnabled = false

                checkErrorList(errorList)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkErrorList(errorList: List<ValidationErrors>) {

        for (error in errorList) {
            when (error) {
                ValidationErrors.TITLE_INVALID -> {
                    inLayoutTitle.isErrorEnabled = true
                    inLayoutTitle.error = getString(R.string.enter_book_title)
                }
                ValidationErrors.ISBN_INVALID -> {
                    inLayoutIsbn.isErrorEnabled = true
                    inLayoutIsbn.error = getString(R.string.invalid_book_isbn)
                }
                ValidationErrors.DATE_INVALID -> {
                    inLayoutDate.isErrorEnabled = true
                    inLayoutDate.error = getString(R.string.invalid_book_date)
                }
            }
        }
    }

    private fun returnToMain() {
        setResult(Activity.RESULT_OK, getResultIntent())
        finish()
    }

    private fun initializeDatePicker(): DatePickerDialog {
        return DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                etDate.setText(dateToStringDatePicker(calendar.time))
            },
            year,
            month,
            day
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Take a pic
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Glide.with(this).load(currentPhotoPath).into(ivCoverThumbnail)
            bookPhotoPath = Uri.fromFile(File(currentPhotoPath)).toString()

        }
        // Load a pic
        else if (requestCode == REQUEST_SELECT_PHOTO && resultCode == RESULT_OK) {
            data?.let {
                val selectedPhoto = data.data
                Glide.with(this).load(selectedPhoto).into(ivCoverThumbnail)
                bookPhotoPath = selectedPhoto?.toString()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "io.bloco.biblioteca.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    // Static

    companion object {

        fun getResultIntent() =
            Intent().also {
                it.putExtra(RESULT_NEW_BOOK, ADD_NEW_BOOK)
            }

        fun getIntent(context: Context): Intent {
            return Intent(context, AddBookActivity::class.java)
        }

        private const val ADD_NEW_BOOK = 10
        private const val RESULT_NEW_BOOK = "ADDED_BOOK"

        // Photo loader
        private const val REQUEST_TAKE_PHOTO = 1
        private const val REQUEST_SELECT_PHOTO = 2
    }
}
