package io.bloco.biblioteca

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), RecyclerAdapter.ListItemLongClick {

    private val booksList = mutableListOf<Book>()
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private val adapter by lazy { RecyclerAdapter(booksList, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BookRepository.initBooks()
        lisfOfBooks.layoutManager = linearLayoutManager
        lisfOfBooks.addItemDecoration(DividerItemDecoration(this))
        lisfOfBooks.itemAnimator = DefaultItemAnimator()
        lisfOfBooks.adapter = adapter

        addBookBtn.setOnClickListener {
            val intentAddBook = AddBookActivity.getIntent(this)
            startActivityForResult(intentAddBook, ADD_NEW_BOOK)
        }
        getAllBooks()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            getAllBooks()
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Timber.d("ResultCanceled")
        }
    }

    override fun itemDelete(book: Book) {
        BookRepository.deleteBook(book) { getAllBooks() }
        //getAllBooks()
    }

    private fun getAllBooks() {
        BookRepository.getBooks { refreshList(it) }
    }

    private fun refreshList(books: List<Book>) {
        booksList.clear()
        booksList.addAll(books)
        adapter.notifyDataSetChanged()
    }

/*    private fun getAllBooks() {
        booksList.clear()
        booksList.addAll(BookRepository.books)
        adapter.notifyDataSetChanged()
    }*/

    companion object {
        private const val ADD_NEW_BOOK = 10
    }
}
