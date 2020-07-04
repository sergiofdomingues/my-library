package com.sergiodomingues.library.bookmain.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sergiodomingues.library.R
import com.sergiodomingues.library.adapter.BooksRecyclerAdapter
import com.sergiodomingues.library.base.BaseActivity
import com.sergiodomingues.library.base.viewmodel.ViewModelFactory
import com.sergiodomingues.library.bookmain.MainViewModel
import com.sergiodomingues.library.bookmain.MainViewModel.Error
import com.sergiodomingues.library.bookmain.MainViewModel.Error.CouldNotDeleteBooks
import com.sergiodomingues.library.bookmain.MainViewModel.Error.CouldNotUpdateList
import com.sergiodomingues.library.booksearch.SearchBookActivity
import com.sergiodomingues.library.common.MessageManager
import com.sergiodomingues.library.helpers.DividerItemDecoration
import com.sergiodomingues.library.model.Book
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(),
    BooksRecyclerAdapter.ListItemLongClick {

    @Inject
    lateinit var messageManager: MessageManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private val adapter by lazy { BooksRecyclerAdapter(booksList, this) }
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private val booksList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recViewBooksList.layoutManager = linearLayoutManager
        recViewBooksList.addItemDecoration(DividerItemDecoration(this))
        recViewBooksList.itemAnimator = DefaultItemAnimator()
        recViewBooksList.adapter = adapter

        fabAddBook.setColorFilter(Color.WHITE)
        fabAddBook.setOnClickListener {
            startActivityForResult(
                SearchBookActivity.getIntent(this), ADD_NEW_BOOK
            )
        }

        viewModel
            .updateBooks()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                refreshList(it)
            }

        viewModel
            .errors()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                showError(it)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.subItemAddNewBook -> {
                return true
            }
            R.id.subItemDeleteAllBooks -> {
                showDeleteAllBooksAlertDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            viewModel.bookListChanged()
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Timber.d("ResultCanceled")
        }
    }

    override fun itemDelete(book: Book) = viewModel.bookDeletionClicked(book)

    private fun refreshList(books: List<Book>) {
        booksList.clear()
        booksList.addAll(books)
        adapter.notifyDataSetChanged()
    }

    private fun showError(error: Error) {
        when (error) {
            CouldNotUpdateList -> messageManager.showError(R.string.main_error_updating_books)
            CouldNotDeleteBooks -> messageManager.showError(R.string.main_error_deleting_books)
        }
    }

    private fun showDeleteAllBooksAlertDialog() =
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.main_alert_dialog_delete_all_title))
            .setMessage(resources.getString(R.string.main_alert_dialog_delete_all_message))
            .setPositiveButton(resources.getString(R.string.main_alert_dialog_accept)) { _, _ ->
                viewModel.deleteAllBooks()
            }
            .setNeutralButton(resources.getString(R.string.main_alert_dialog_cancel), null)
            .show()

    companion object {
        private const val ADD_NEW_BOOK = 10
    }
}
