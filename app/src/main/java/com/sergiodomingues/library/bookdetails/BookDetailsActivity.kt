package com.sergiodomingues.library.bookdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sergiodomingues.library.R
import com.sergiodomingues.library.base.BaseActivity
import com.sergiodomingues.library.helpers.DateHelpers.dateToString
import com.sergiodomingues.library.helpers.ImageLoader
import com.sergiodomingues.library.model.Book
import kotlinx.android.synthetic.main.activity_book_details.*
import javax.inject.Inject

class BookDetailsActivity : BaseActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader
    private var selectedBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setContentView(R.layout.activity_book_details)

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
        selectedBook.imageCover?.let {
            if (it.isNotEmpty()) imageLoader.loadPhotoCoverInto(it, ivBookCover)
        }
    }

    companion object {
        private const val BOOK_KEY = "BOOK"

        fun getIntent(context: Context, book: Book) =
            Intent(context, BookDetailsActivity::class.java).also {
                it.putExtra(BOOK_KEY, book)
            }
    }
}
