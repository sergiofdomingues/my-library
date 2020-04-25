package io.bloco.biblioteca.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import io.bloco.biblioteca.R
import io.bloco.biblioteca.adapter.BooksRecyclerAdapter
import io.bloco.biblioteca.database.BookRepository
import io.bloco.biblioteca.helpers.DividerItemDecoration
import io.bloco.biblioteca.helpers.FileManager
import io.bloco.biblioteca.model.Book
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), BooksRecyclerAdapter.ListItemLongClick {

    @Inject
    lateinit var bookRepository: BookRepository
    private val adapter by lazy { BooksRecyclerAdapter(booksList, this) }

    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private val booksList = mutableListOf<Book>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setContentView(R.layout.activity_main)

        bookRepository.initBooksInDb() // Fake some books
        recViewBooksList.layoutManager = linearLayoutManager
        recViewBooksList.addItemDecoration(DividerItemDecoration(this))
        recViewBooksList.itemAnimator = DefaultItemAnimator()
        recViewBooksList.adapter = adapter

        fabAddBook.setOnClickListener {
            startActivityForResult(
                SearchBookActivity.getIntent(
                    this
                ),
                ADD_NEW_BOOK
            )
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
        book.uriCover?.let {
            if (!it.startsWith("http")) {
                val fileManager = FileManager(this)
                doAsync {
                    fileManager.deletePhotoFile(Uri.parse(book.uriCover))
                }
            }
        }
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
