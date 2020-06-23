package com.sergiodomingues.library.bookdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
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
        setSupportActionBar(toolbar)
        setNavigation(R.drawable.ic_close_left)

        selectedBook = intent.getParcelableExtra(BOOK_KEY)
        selectedBook?.let { initDetailFields(it) }
        appBarLayout.addOnOffsetChangedListener(AppBarLayoutOffsetChangedListener())
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

    inner class AppBarLayoutOffsetChangedListener() : AppBarLayout.OnOffsetChangedListener {
        private var scrollRange = -1
        override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
            if (scrollRange == -1) {
                scrollRange = appBarLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                collapsingToolbarLayout.title = getString(R.string.book_info_activity_label)
            } else {
                collapsingToolbarLayout.title = " "
            }
        }
    }
}