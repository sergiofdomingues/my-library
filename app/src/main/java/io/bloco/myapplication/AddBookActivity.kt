package io.bloco.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        strictMode()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu?.getItem(0)?.icon?.let {
            DrawableCompat.setTint(
                it, ContextCompat.getColor(this, R.color.accent)
            )
        }
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id: Int? = item?.itemId
        if (id == R.id.saveBook) {
            val newBook = Book(
                etTitle.text.toString(),
                etAuthor.text.toString(),
                etDate.text.toString(),
                etISBN.text.toString(),
                cbRead.isChecked
            )

            if (bookDetailsAreFilled(newBook)) {
                BookRepository.addBook(newBook)
                setResult(Activity.RESULT_OK, getResultIntent())
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bookDetailsAreFilled(book: Book): Boolean {
        if (book.title.equals(""))
            return false

        return true
    }

    companion object {

        fun getResultIntent() =
            Intent().also {
                it.putExtra(RESULT_NEW_BOOK, ADD_NEW_BOOK)
            }

        private const val ADD_NEW_BOOK = 10
        private const val RESULT_NEW_BOOK = "ADDED_BOOK"
    }

    private fun strictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .build()
        )
    }
}
