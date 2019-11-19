package io.bloco.biblioteca.app.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import io.bloco.biblioteca.R
import io.bloco.biblioteca.helpers.Helpers.dateToString
import io.bloco.biblioteca.model.Book
import kotlinx.android.synthetic.main.activity_book_info.*

class BookInfoActivity : AppCompatActivity() {

    private var selectedBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)

        selectedBook = intent.getParcelableExtra(BOOK_KEY)
        initDetailFields()
        loadImageFromDevice()
    }

    private fun initDetailFields() {
        tvBookTitle.text = selectedBook?.title
        if (tvBookTitle.text == "")
            tvBookTitle.text = getString(R.string.no_title)

        tvBookAuthor.text = selectedBook?.author
        if (tvBookAuthor.text == "")
            tvBookAuthor.text = getString(R.string.no_author)

        tvBookIsbn.text = selectedBook?.isbn
        if (tvBookIsbn.text == "")
            tvBookIsbn.text = getString(R.string.no_isbn)

        tvBookDate.text = dateToString(selectedBook?.publishDate)
        if (tvBookDate.text == "")
            tvBookDate.text = getString(R.string.no_date)

        Glide.with(this).load(selectedBook?.uriCover).into(ivBookCover)
    }

    private fun loadImageFromDevice() {
        selectedBook?.uriCover?.let {
            Glide.with(this).load(selectedBook?.uriCover).into(ivBookCover)
        }
    }

    companion object {
        private const val BOOK_KEY = "BOOK"

        fun getIntent(context: Context, book: Book) =
            Intent(context, BookInfoActivity::class.java).also {
                it.putExtra(BOOK_KEY, book)
            }
    }
}