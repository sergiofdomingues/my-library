package io.bloco.biblioteca.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.bloco.biblioteca.App
import io.bloco.biblioteca.R
import io.bloco.biblioteca.helpers.DateHelpers.dateToString
import io.bloco.biblioteca.helpers.ImageLoader
import io.bloco.biblioteca.model.Book
import kotlinx.android.synthetic.main.activity_book_info.*
import javax.inject.Inject

class BookInfoActivity : AppCompatActivity() {

    //@Inject lateinit var selectedBook: Book
    @Inject lateinit var imageLoader: ImageLoader
    private var selectedBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).component.inject(this)

        setContentView(R.layout.activity_book_info)
        selectedBook = intent.getParcelableExtra(BOOK_KEY)
        selectedBook?.let {
            initDetailFields(it)
        }
    }

    private fun initDetailFields(selectedBook: Book) {
        tvBookTitle.text = selectedBook.title
        tvBookAuthor.text = selectedBook.author ?: ""
        tvBookIsbn.text = selectedBook.isbn ?: ""
        tvBookDate.text = dateToString(selectedBook.publishDate)
        selectedBook.uriCover?.let { imageLoader.loadImageInto(it, ivBookCover) }
    }

    companion object {
        private const val BOOK_KEY = "BOOK"

        fun getIntent(context: Context, book: Book) =
            Intent(context, BookInfoActivity::class.java).also {
                it.putExtra(BOOK_KEY, book)
            }
    }
}
