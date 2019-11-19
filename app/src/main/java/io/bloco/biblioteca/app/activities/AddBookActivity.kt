package io.bloco.biblioteca.app.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import io.bloco.biblioteca.R
import io.bloco.biblioteca.app.App
import io.bloco.biblioteca.helpers.Helpers
import io.bloco.biblioteca.helpers.Helpers.dateToStringDatePicker
import io.bloco.biblioteca.helpers.Validation
import io.bloco.biblioteca.helpers.ValidationErrors
import io.bloco.biblioteca.helpers.files.FileManager
import io.bloco.biblioteca.helpers.imageloader.ImageLoader
import io.bloco.biblioteca.model.Book
import io.bloco.biblioteca.model.FoundBook
import kotlinx.android.synthetic.main.activity_add_book.*
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.*


class AddBookActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val datePicker by lazy { initializeDatePicker() }
    private val bookRepository by lazy { (application as App).getBookRepository() }
    private var bookPhotoPath: String? = null
    private var chosenBook: FoundBook? = null
    private var bookSuccessfullyAdded = false
    private val fileManager by lazy { FileManager(this) }
    private val imageLoader by lazy { ImageLoader(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        chosenBook = intent.getParcelableExtra(CHOSEN_BOOK)
        if (chosenBook != null) {
            initDetailFields()
            bookPhotoPath = chosenBook?.thumbnail
        }
        etDate.setOnClickListener { datePicker.show() }
        btnTakePhoto.setOnClickListener { dispatchTakePictureIntent() }
        btnUploadPhoto.setOnClickListener { dispatchUploadPictureIntent() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    private fun showLayoutErrors() {
        inLayoutTitle.isErrorEnabled = false
        inLayoutIsbn.isErrorEnabled = false
        inLayoutDate.isErrorEnabled = false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int? = item?.itemId
        if (id == R.id.itemSaveBook) {

            val newBook = makeBookFromFields()
            val errorList = Validation().validateBook(newBook)
            if (errorList.isEmpty()) {
                bookRepository.addBook(newBook) { returnToSearchActivity() }
                return true
            } else {
                showLayoutErrors()
                checkErrorList(errorList)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Take a pic
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            fileManager.currentPhotoPath?.let {
                imageLoader.loadImageInto(it, ivCoverThumbnail)
                bookPhotoPath = Uri.fromFile(File(it)).toString()
            }
        }
        // Load a pic
        if (requestCode == REQUEST_SELECT_PHOTO && resultCode == RESULT_OK) {
            data?.data?.let {
                fileManager.copyPhotoFileToAppStorage(it)
                fileManager.currentPhotoPath?.let { path ->
                    imageLoader.loadImageInto(path, ivCoverThumbnail)
                    bookPhotoPath = Uri.fromFile(File(path)).toString()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fileManager.deletePhotoFile(bookPhotoPath)
    }

    override fun onDestroy() {
        if (!bookSuccessfullyAdded)
            fileManager.deletePhotoFile(bookPhotoPath)
        super.onDestroy()
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

    private fun returnToSearchActivity() {
        bookSuccessfullyAdded = true
        setResult(
            Activity.RESULT_OK,
            getResultIntent()
        )
        finish()
    }

    private fun dispatchUploadPictureIntent() {
        // Create intent to select photo from the gallery
        val intentOpenGallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        //permissions
        intentOpenGallery.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        // Start the intent with a request code
        startActivityForResult(
            intentOpenGallery,
            REQUEST_SELECT_PHOTO
        )
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    fileManager.createImageFile()
                } catch (e: IOException) {
                    Timber.e(e, "Error occurred while creating the File")
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this, getString(R.string.fileprovider), it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(
                        takePictureIntent,
                        REQUEST_TAKE_PHOTO
                    )
                }
            }
        }
    }

    private fun initDetailFields() {
        etTitle.setText(chosenBook?.title)
        etAuthor.setText(chosenBook?.author)
        etDate.setText(chosenBook?.publishedDate)
        etIsbn.setText(chosenBook?.isbn)
        chosenBook?.thumbnail?.let {
            imageLoader.loadImageInto(it, ivCoverThumbnail)
        }
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

    private fun makeBookFromFields(): Book {
        return Book(
            title = etTitle.text.toString(),
            author = etAuthor.text.toString(),
            publishDate = Helpers.stringFromGApiToDate(etDate.text.toString()),
            isbn = etIsbn.text.toString(),
            read = cbRead.isChecked,
            uriCover = bookPhotoPath
        )
    }

    // Static

    companion object {

        fun getResultIntent() =
            Intent().also {
                it.putExtra(
                    RESULT_NEW_BOOK,
                    ADD_NEW_BOOK
                )
            }

        fun getIntent(context: Context): Intent {
            return Intent(context, AddBookActivity::class.java)
        }

        fun getIntent(context: Context, book: FoundBook) =
            Intent(context, AddBookActivity::class.java).also {
                it.putExtra(CHOSEN_BOOK, book)
            }

        private const val ADD_NEW_BOOK = 10
        private const val RESULT_NEW_BOOK = "ADDED_BOOK"
        private const val CHOSEN_BOOK = "ADDING_BOOK"

        // Photo loader
        private const val REQUEST_TAKE_PHOTO = 1
        private const val REQUEST_SELECT_PHOTO = 2
    }
}
