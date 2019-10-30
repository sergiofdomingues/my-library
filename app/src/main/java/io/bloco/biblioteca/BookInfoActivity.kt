package io.bloco.biblioteca

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_book_info.*

class BookInfoActivity : AppCompatActivity() {

    private var selectedBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)

        selectedBook = intent.getParcelableExtra(BOOK_KEY)
        bookTitle.text = selectedBook?.title
        bookAuthor.text = selectedBook?.author
        bookIsbn.text = selectedBook?.isbn
        bookDate.text = selectedBook?.publishDate
    }

    companion object {
        private const val BOOK_KEY = "BOOK"

        fun getIntent(context: Context, book: Book) =
            Intent(context, BookInfoActivity::class.java).also {
                it.putExtra(BOOK_KEY, book)
            }
    }
}
