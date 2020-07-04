package com.sergiodomingues.library.booksearch

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.support.v7.widget.queryTextChanges
import com.jakewharton.rxbinding2.view.clicks
import com.sergiodomingues.library.R
import com.sergiodomingues.library.adapter.SearchBooksRecyclerAdapter
import com.sergiodomingues.library.base.BaseActivity
import com.sergiodomingues.library.base.viewmodel.ViewModelFactory
import com.sergiodomingues.library.bookadd.ui.AddBookActivity
import com.sergiodomingues.library.booksearch.SearchBookViewModel.Error.ErrorSearching
import com.sergiodomingues.library.common.MessageManager
import com.sergiodomingues.library.helpers.DividerItemDecoration
import com.sergiodomingues.library.model.FoundBook
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_search_book.*
import timber.log.Timber
import javax.inject.Inject

class SearchBookActivity : BaseActivity(), SearchBooksRecyclerAdapter.ListItemClick {
    @Inject
    lateinit var messageManager: MessageManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SearchBookViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SearchBookViewModel::class.java)
    }
    private val adapter by lazy { SearchBooksRecyclerAdapter(foundBooksList, this) }
    private val foundBooksList = mutableListOf<FoundBook>()
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setContentView(R.layout.activity_search_book)
        setSupportActionBar(toolbar)
        setNavigation(R.drawable.ic_close_left)

        fabAddBookManually.setColorFilter(Color.WHITE)
        recViewBooksFoundList.layoutManager = linearLayoutManager
        recViewBooksFoundList.addItemDecoration(
            DividerItemDecoration(
                this
            )
        )
        recViewBooksFoundList.itemAnimator = DefaultItemAnimator()
        recViewBooksFoundList.adapter = adapter

        viewModel
            .refreshBooks()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                refreshBooks(it)
            }

        viewModel
            .errors()
            .bindToLifecycle(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    ErrorSearching -> messageManager.showError(R.string.search_error)
                }
            }

        search
            .queryTextChanges()
            .bindToLifecycle(this)
            .subscribe {
                viewModel.searchQueryChanged(it.toString())
            }

        fabAddBookManually
            .clicks()
            .bindToLifecycle(this)
            .subscribe {
                startActivityForResult(AddBookActivity.getIntent(this), ADD_NEW_BOOK)
            }
    }

    private fun refreshBooks(foundBooks: List<FoundBook>) {
        foundBooksList.clear()
        foundBooksList.addAll(foundBooks)
        adapter.notifyDataSetChanged()
    }

    override fun itemClick(book: FoundBook) {
        startActivityForResult(
            AddBookActivity.getIntent(this, book),
            ADD_NEW_BOOK
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            returnToMainActivity()
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Timber.d("ResultCanceled")
        }
    }

    private fun returnToMainActivity() {
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
            return Intent(context, SearchBookActivity::class.java)
        }

        private const val ADD_NEW_BOOK = 10
        private const val RESULT_NEW_BOOK = "ADDED_BOOK"
    }
}
