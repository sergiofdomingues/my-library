package io.bloco.biblioteca.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.FileProvider
import io.bloco.biblioteca.R
import io.bloco.biblioteca.database.BookRepository
import io.bloco.biblioteca.helpers.*
import io.bloco.biblioteca.helpers.DateHelpers.dateToStringDatePicker
import io.bloco.biblioteca.model.Book
import io.bloco.biblioteca.model.FoundBook
import kotlinx.android.synthetic.main.activity_add_book.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject

class AddBookActivity : BaseActivity() {
    @Inject lateinit var bookRepository: BookRepository
    @Inject lateinit var fileManager: FileManager
    @Inject lateinit var imageLoader: ImageLoader
    @Inject lateinit var intentManager: IntentManager

    private val datePicker by lazy { initializeDatePicker() }
    private val calendar = Calendar.getInstance()
    private var bookSuccessfullyAdded = false
    private var currentPhotoPath: String? = null
    private var tempFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)

        setContentView(R.layout.activity_add_book)
        val chosenBook: FoundBook? = intent.getParcelableExtra(CHOSEN_BOOK)
        chosenBook?.let {
            initDetailFields(it)
            currentPhotoPath = chosenBook.thumbnail
        }
        etDate.setOnClickListener { datePicker.show() }
        btnTakePhoto.setOnClickListener { dispatchTakePictureIntent() }
        btnUploadPhoto.setOnClickListener { dispatchUploadPictureIntent() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int? = item.itemId
        if (id == R.id.itemSaveBook) {

            val newBook = makeBookFromFields()
            val errorList = Validation().validateBook(newBook)
            if (errorList.isEmpty()) {
                bookSuccessfullyAdded = true
                bookRepository.addBook(newBook) { returnToSearchActivity() }
                return true
            } else {
                bookSuccessfullyAdded = false
                resetLayoutErrors()
                showLayoutErrors(errorList)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Take a pic
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            currentPhotoPath = tempFile?.path
            currentPhotoPath?.let {
                imageLoader.loadImageInto(it, ivCoverThumbnail)
            }
        }

        // Load a pic
        if (requestCode == REQUEST_SELECT_PHOTO && resultCode == RESULT_OK) {
            data?.data?.let {
                loadPicAsync(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("photoPath", currentPhotoPath)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        currentPhotoPath = savedInstanceState.getString("photoPath")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        if (!bookSuccessfullyAdded)
            doAsync { fileManager.deletePhotoFile(tempFile?.path) }
        super.onDestroy()
    }

    private fun dispatchTakePictureIntent() {
        takePicAsync { startTakePicIntent(it) }
    }

    private fun dispatchUploadPictureIntent() {
        startActivityForResult(
            intentManager.getUploadPictureIntent(),
            REQUEST_SELECT_PHOTO
        )
    }

    private fun takePicAsync(onComplete: ((File) -> Unit)) {
        doAsync {
            try {
                tempFile?.let { fileManager.deletePhotoFile(it.path) }
                tempFile = fileManager.createImageFile().also { file ->
                    uiThread {
                        onComplete.invoke(file)
                    }
                }
            } catch (e: IOException) {
                Timber.e(e, "Error occurred while creating the File")
            }
        }
    }

    private fun loadPicAsync(picUri: Uri) {
        doAsync {
            tempFile?.let { fileManager.deletePhotoFile(it.path) }
            tempFile = fileManager.copyPhotoFileToAppStorage(picUri)
            currentPhotoPath = tempFile?.path
            uiThread {
                currentPhotoPath?.let { path ->
                    imageLoader.loadImageInto(path, ivCoverThumbnail)
                }
            }
        }
    }

    private fun startTakePicIntent(tempFile: File) {
        val photoURI: Uri = FileProvider.getUriForFile(
            this, getString(R.string.fileprovider), tempFile
        )
        val takePicIntent =
            intentManager.getTakePictureIntent(MediaStore.EXTRA_OUTPUT, photoURI)
        intent.resolveActivity(packageManager)?.also {
            startActivityForResult(
                takePicIntent,
                REQUEST_TAKE_PHOTO
            )
        }
    }

    private fun showLayoutErrors(errorList: List<ValidationErrors>) {
        for (error in errorList) {
            when (error) {
                ValidationErrors.TITLE_INVALID -> {
                    inLayoutTitle.isErrorEnabled = true
                    inLayoutTitle.error = getString(R.string.enter_book_title)
                }
                ValidationErrors.DATE_INVALID -> {
                    inLayoutDate.isErrorEnabled = true
                    inLayoutDate.error = getString(R.string.invalid_book_date)
                }
            }
        }
    }

    private fun initDetailFields(chosenBook: FoundBook) {
        etTitle.setText(chosenBook.title)
        etAuthor.setText(chosenBook.author)
        etDate.setText(chosenBook.publishedDate)
        etIsbn.setText(chosenBook.isbn)
        chosenBook.thumbnail?.let {
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
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun makeBookFromFields(): Book {
        currentPhotoPath?.let { path ->
            if (path.startsWith(applicationInfo.dataDir, true)) // Book manually added
                currentPhotoPath = Uri.fromFile(File(path)).toString()
        }

        return Book(
            title = etTitle.text.toString(),
            author = etAuthor.text.toString(),
            publishDate = DateHelpers.parseStringToDate(etDate.text.toString()),
            isbn = etIsbn.text.toString(),
            read = cbRead.isChecked,
            uriCover = currentPhotoPath
        )
    }

    private fun resetLayoutErrors() {
        inLayoutTitle.isErrorEnabled = false
        inLayoutDate.isErrorEnabled = false
    }

    private fun returnToSearchActivity() {
        bookSuccessfullyAdded = true
        setResult(
            Activity.RESULT_OK,
            getResultIntent()
        )
        finish()
    }

    private fun getResultIntent() =
        Intent().also {
            it.putExtra(
                RESULT_NEW_BOOK,
                ADD_NEW_BOOK
            )
        }

    // Static

    companion object {

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
