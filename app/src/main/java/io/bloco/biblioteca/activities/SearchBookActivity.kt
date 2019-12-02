package io.bloco.biblioteca.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import io.bloco.biblioteca.R
import io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter
import io.bloco.biblioteca.api.ApiCaller
import io.bloco.biblioteca.common.MessageManager
import io.bloco.biblioteca.model.FoundBook
import kotlinx.android.synthetic.main.activity_search_book.*
import javax.inject.Inject

class SearchBookActivity : BaseActivity(), SearchBooksRecyclerAdapter.ListItemClick,
    SearchBookPresenter.View {

    @Inject lateinit var presenter: SearchBookPresenter
    @Inject lateinit var messageManager: MessageManager
    @Inject lateinit var api: ApiCaller
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
        presenter.start(this)
    }

    override fun itemClick(book: FoundBook) {
        presenter.itemClicked(this, book)
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
        if (item.itemId == R.id.itemAddBook) {
            presenter.addBookClicked(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResultPerformed(resultCode)
    }

    private inner class QueryTextListener : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            onQueryTextChange(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            presenter.queryTextChanged(newText)
            return true
        }
    }

    // Interface

    override fun showError(errorId: Int) {
        messageManager.showError(errorId)
    }

    override fun updateFoundBooksList(newList: List<FoundBook>) {
        foundBooksList.clear()
        foundBooksList.addAll(newList)
        adapter.notifyDataSetChanged()
    }

    override fun returnToMainActivity(intent: Intent) {
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun startAddBookActivityForResult(intent: Intent, requestCode: Int) {
        startActivityForResult(intent, requestCode)
    }
}