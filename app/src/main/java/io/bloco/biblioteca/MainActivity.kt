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
    private val bookRepository by lazy { (application as App).getBookRepository() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookRepository.initBooksInDb() // Fake some books
        recViewBooksList.layoutManager = linearLayoutManager
        recViewBooksList.addItemDecoration(DividerItemDecoration(this))
        recViewBooksList.itemAnimator = DefaultItemAnimator()
        recViewBooksList.adapter = adapter

        fabAddBook.setOnClickListener {
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
        bookRepository.deleteBook(book) { getAllBooks() }
    }

    private fun getAllBooks() {
        bookRepository.getBooks { refreshList(it) }
    }

    private fun refreshList(books: List<Book>) {
        booksList.clear()
        booksList.addAll(books)
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val ADD_NEW_BOOK = 10
    }
}
