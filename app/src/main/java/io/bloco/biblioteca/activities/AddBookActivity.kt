package io.bloco.biblioteca.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.FileProvider
import io.bloco.biblioteca.R
import io.bloco.biblioteca.helpers.DateHelpers
import io.bloco.biblioteca.helpers.DateHelpers.dateToStringDatePicker
import io.bloco.biblioteca.helpers.ImageLoader
import io.bloco.biblioteca.helpers.IntentManager.Companion.CHOSEN_BOOK
import io.bloco.biblioteca.helpers.IntentManager.Companion.REQUEST_SELECT_PHOTO
import io.bloco.biblioteca.helpers.IntentManager.Companion.REQUEST_TAKE_PHOTO
import io.bloco.biblioteca.helpers.ValidationErrors
import io.bloco.biblioteca.model.Book
import io.bloco.biblioteca.model.FoundBook
import kotlinx.android.synthetic.main.activity_add_book.*
import java.io.File
import java.util.*
import javax.inject.Inject

class AddBookActivity : BaseActivity(), AddBookPresenter.View {
    @Inject lateinit var presenter: AddBookPresenter
    @Inject lateinit var imageLoader: ImageLoader
    private val datePicker by lazy { initializeDatePicker() }
    private val calendar = Calendar.getInstance()
    private var bookSuccessfullyAdded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)

        setContentView(R.layout.activity_add_book)
        val chosenBook: FoundBook? = intent.getParcelableExtra(CHOSEN_BOOK)
        chosenBook?.let {
            initDetailFields(it)
            presenter.setCurrentPhotoPath(chosenBook.thumbnail)
        }
        etDate.setOnClickListener { datePicker.show() }
        btnTakePhoto.setOnClickListener { dispatchTakePictureIntent() }
        btnUploadPhoto.setOnClickListener { dispatchUploadPictureIntent() }
        presenter.start(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int? = item.itemId
        if (id == R.id.itemSaveBook) {
            val newBook = makeBookFromFields()
            presenter.addBook(newBook)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun loadImage(photoPath: String) {
        imageLoader.loadImageInto(photoPath, ivCoverThumbnail)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Take a pic
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            presenter.requestCapturedPhotoLoad()
        }
        // Load a pic
        if (requestCode == REQUEST_SELECT_PHOTO && resultCode == RESULT_OK) {
            data?.data?.let {
                presenter.loadPicAsync(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("photoPath", presenter.getCurrentPhotoPath())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val currentPhotoPath = savedInstanceState.getString("photoPath")
        presenter.setCurrentPhotoPath(currentPhotoPath)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        if (!bookSuccessfullyAdded)
            presenter.deletePhotoPhileIfNeeded()
        super.onDestroy()
    }

    private fun dispatchTakePictureIntent() {
        presenter.takePic()
    }

    private fun dispatchUploadPictureIntent() {
        presenter.uploadPic()
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
        val path = presenter.preparePhotoPath(applicationInfo.dataDir)
        return Book(
            title = etTitle.text.toString(),
            author = etAuthor.text.toString(),
            publishDate = DateHelpers.parseStringToDate(etDate.text.toString()),
            isbn = etIsbn.text.toString(),
            read = cbRead.isChecked,
            uriCover = path
        )
    }

    private fun resetLayoutErrors() {
        inLayoutTitle.isErrorEnabled = false
        inLayoutDate.isErrorEnabled = false
    }

    // interface

    override fun bookSuccessfullyAdded(state: Boolean) {
        bookSuccessfullyAdded = state
    }

    override fun showLayoutErrors(errorList: List<ValidationErrors>) {
        resetLayoutErrors()
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

    override fun startTakePic(requestCode: Int, tempFile: File) {
        val photoURI: Uri = FileProvider.getUriForFile(
            this, getString(R.string.fileprovider), tempFile
        )
        val intent = presenter.getIntentUriAuthorization(photoURI)
        intent.resolveActivity(packageManager)?.also {
            startActivityForResult(
                intent,
                requestCode
            )
        }
    }

    override fun startUploadPic(intent: Intent, requestCode: Int) {
        startActivityForResult(intent, requestCode)
    }

    override fun returnToSearchActivity(result: Int, intent: Intent) {
        setResult(result, intent)
        finish()
    }
}