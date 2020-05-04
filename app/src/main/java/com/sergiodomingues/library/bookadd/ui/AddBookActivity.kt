package com.sergiodomingues.library.bookadd.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding2.view.clicks
import com.sergiodomingues.library.R
import com.sergiodomingues.library.base.BaseActivity
import com.sergiodomingues.library.base.viewmodel.ViewModelFactory
import com.sergiodomingues.library.bookadd.AddBookViewModel
import com.sergiodomingues.library.helpers.*
import com.sergiodomingues.library.helpers.DateHelpers.dateToStringDatePicker
import com.sergiodomingues.library.helpers.DateHelpers.parseStringToDate
import com.sergiodomingues.library.model.Book
import com.sergiodomingues.library.model.FoundBook
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_add_book.*
import java.util.*
import javax.inject.Inject

class AddBookActivity : BaseActivity() {

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

        val chosenBook: FoundBook? = intent.getParcelableExtra(
            CHOSEN_BOOK
        )
        etDate.setOnClickListener { datePicker.show() }

        viewModel
            .initBookDetailFields()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                initBookDetailFields(it)
            }

        chosenBook?.let { viewModel.chosenBookProvidedByIntent(it) }

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
                imageLoader.loadPhotoCoverInto(it, ivCoverThumbnail)
            }

        viewModel
            .returnToSearch()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                returnToSearchActivity()
            }

        viewModel
            .showInputFieldErrors()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                showInvalidFieldErrors(it)
            }

        activityResults()
            .bindToLifecycle(this)
            .map { intentResultDispatcherBook.dispatch(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.coverPhotoIntentOutcome(it)
            }
    }

    private fun showInvalidFieldErrors(errorList: List<ValidationErrors>) {
        bookSuccessfullyAdded = false
        resetLayoutErrors()
        showLayoutErrors(errorList)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int? = item.itemId
        if (id == R.id.itemSaveBook) {
            viewModel.saveBookClicked(makeBookFromFields())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu)
        return true
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

    private fun initBookDetailFields(chosenBook: FoundBook) {
        etTitle.setText(chosenBook.title)
        etAuthor.setText(chosenBook.author)
        etDate.setText(chosenBook.publishedDate)
        etIsbn.setText(chosenBook.isbn)
        chosenBook.imageCover?.let {
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
            publishDate = parseStringToDate(
                etDate.text.toString()
            ),
            isbn = etIsbn.text.toString(),
            read = cbRead.isChecked
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
