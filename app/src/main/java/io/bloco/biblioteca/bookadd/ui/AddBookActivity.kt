package io.bloco.biblioteca.bookadd.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import io.bloco.biblioteca.R
import io.bloco.biblioteca.base.BaseActivity
import io.bloco.biblioteca.base.viewmodel.ViewModelFactory
import io.bloco.biblioteca.bookadd.AddBookViewModel
import io.bloco.biblioteca.database.BookRepository
import io.bloco.biblioteca.helpers.*
import io.bloco.biblioteca.helpers.DateHelpers.dateToStringDatePicker
import io.bloco.biblioteca.model.Book
import io.bloco.biblioteca.model.FoundBook
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_add_book.*
import java.util.*
import javax.inject.Inject

class AddBookActivity : BaseActivity() {

    @Inject
    lateinit var bookRepository: BookRepository

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var intentBuilder: IntentBuilder

    @Inject
    lateinit var intentResultDispatcherBook: BookCoverIntentResultDispatcher

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddBookViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(AddBookViewModel::class.java)
    }
    private val datePicker by lazy { initializeDatePicker() }
    private val calendar = Calendar.getInstance()
    private var bookSuccessfullyAdded = false
    private var currentPhotoPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setContentView(R.layout.activity_add_book)

        changeBookCover
            .clicks()
            .bindToLifecycle(this)
            .subscribe {
                viewModel.startChangingBookCover()
            }

        viewModel
            .startPickPhotoIntentChooser()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startActivityForResult(
                    intentBuilder.getImageChooserIntent(this, it),
                    REQUEST_PHOTO_INTENT_CHOOSER
                )
            }

        viewModel
            .changeCover()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                currentPhotoPath = it.toString()
                imageLoader.loadPhotoCoverInto(it, ivCoverThumbnail)
            }

        activityResults()
            .bindToLifecycle(this)
            .map { intentResultDispatcherBook.dispatch(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.coverPhotoIntentOutcome(it)
            }

        val chosenBook: FoundBook? = intent.getParcelableExtra(CHOSEN_BOOK)
        chosenBook?.let {
            initDetailFields(it)
            currentPhotoPath = chosenBook.thumbnail
        }
        etDate.setOnClickListener { datePicker.show() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int? = item.itemId
        if (id == R.id.itemSaveBook) {

            val newBook = makeBookFromFields()
            val errorList = Validation().validateBook(newBook)
            if (errorList.isEmpty()) {
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
        if (!bookSuccessfullyAdded) {
            currentPhotoPath?.let {
                viewModel.addingBookCanceled(it)
            }
        }
        super.onDestroy()
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
            imageLoader.loadPhotoCoverInto(it, ivCoverThumbnail)
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

    private fun makeBookFromFields() =
        Book(
            title = etTitle.text.toString(),
            author = etAuthor.text.toString(),
            publishDate = DateHelpers.parseStringToDate(etDate.text.toString()),
            isbn = etIsbn.text.toString(),
            read = cbRead.isChecked,
            uriCover = currentPhotoPath
        )

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
        const val REQUEST_PHOTO_INTENT_CHOOSER = 11
    }
}
