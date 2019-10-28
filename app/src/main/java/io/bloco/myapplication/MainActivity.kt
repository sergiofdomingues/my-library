package io.bloco.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerAdapter.ListItemLongClick {
    override fun itemDelete(book: Book) {
        BookRepository.deleteBook(book)
        getAllBooks()
    }

    private var bookRepository: BookRepository = BookRepository
    private val booksList: ArrayList<Book> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = linearLayoutManager
        myRecyclerView.addItemDecoration(DividerItemDecoration(this))
        myRecyclerView.itemAnimator = DefaultItemAnimator()
        adapter = RecyclerAdapter(booksList, this)
        myRecyclerView.adapter = adapter

        fabAddBook.setOnClickListener {
            val intentAddBook = Intent(this, AddBookActivity::class.java)
            this.startActivityForResult(intentAddBook, ADD_NEW_BOOK)
        }
    }

    override fun onStart() {
        super.onStart()
        if (booksList.size == 0) {
            getAllBooks()
        }
    }

    private fun getAllBooks() {
        booksList.clear()
        booksList.addAll(bookRepository.dataBase)
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NEW_BOOK) {
            if (resultCode == RESULT_OK) {
                val result = data?.getIntExtra(RESULT_NEW_BOOK, 0)
                if (result == ADD_NEW_BOOK) {
                    getAllBooks()
                    adapter.notifyItemInserted(booksList.size - 1)
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.d("ResultCanceled", "Add New Book Canceled")
            }
        }
    }

    companion object {
        private const val ADD_NEW_BOOK = 10
        private const val RESULT_NEW_BOOK = "ADDED_BOOK"
    }
}
