package io.bloco.biblioteca.booksearch

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import io.bloco.biblioteca.R
import io.bloco.biblioteca.base.BaseActivity
import io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter
import io.bloco.biblioteca.api.ApiCaller
import io.bloco.biblioteca.api.BookSearchResult
import io.bloco.biblioteca.bookadd.ui.AddBookActivity
import io.bloco.biblioteca.common.MessageManager
import io.bloco.biblioteca.model.FoundBook
import kotlinx.android.synthetic.main.activity_search_book.*
import timber.log.Timber
import javax.inject.Inject

class SearchBookActivity : BaseActivity(), SearchBooksRecyclerAdapter.ListItemClick {
    @Inject
    lateinit var messageManager: MessageManager
    @Inject
    lateinit var api: ApiCaller
    private val adapter by lazy { SearchBooksRecyclerAdapter(foundBooksList, this) }

    private val foundBooksList = mutableListOf<FoundBook>()
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setContentView(R.layout.activity_search_book)

        recViewBooksFoundList.layoutManager = linearLayoutManager
        recViewBooksFoundList.addItemDecoration(
            io.bloco.biblioteca.helpers.DividerItemDecoration(
                this
            )
        )
        recViewBooksFoundList.itemAnimator = DefaultItemAnimator()
        recViewBooksFoundList.adapter = adapter
    }

    override fun itemClick(book: FoundBook) {
        startActivityForResult(
            AddBookActivity.getIntent(this, book),
            ADD_NEW_BOOK
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        (menu.findItem(R.id.itemSearchBook).actionView as SearchView).apply {
            this.queryHint = getString(R.string.search_query_hint)
            this.isIconified = false
            this.onActionViewExpanded()
            this.setOnQueryTextListener(QueryTextListener())
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int? = item.itemId
        if (id == R.id.itemAddBook) {
            startActivityForResult(
                AddBookActivity.getIntent(this),
                ADD_NEW_BOOK
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            returnToMainActivity()
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Timber.d("ResultCanceled")
        }
    }

    private fun performBookSearch(query: String) {
        api.performSearchByQuery(query) { refreshFoundBooksList(it) }
    }

    private fun refreshFoundBooksList(response: (BookSearchResult<Any>)) {
        when (response) {
            is BookSearchResult.Success -> {
                foundBooksList.clear()
                foundBooksList.addAll(response.data as List<FoundBook>)
                adapter.notifyDataSetChanged()
            }
            is BookSearchResult.Error ->
                messageManager.showError(R.string.connection_error)
        }
    }

    private fun returnToMainActivity() {
        setResult(
            Activity.RESULT_OK,
            getResultIntent()
        )
        finish()
    }

    private inner class QueryTextListener : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            onQueryTextChange(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            performBookSearch(newText)
            return true
        }
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
