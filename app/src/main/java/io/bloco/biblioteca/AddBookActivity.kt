package io.bloco.biblioteca

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.bloco.biblioteca.helpers.Helpers
import kotlinx.android.synthetic.main.activity_add_book.*
import java.text.SimpleDateFormat
import java.util.*

class AddBookActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val datePicker by lazy { initializeDatePicker() }
    private val bookRepository by lazy { (application as App).getBookRepository() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        etDate.setOnClickListener { datePicker.show() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int? = item?.itemId
        if (id == R.id.saveBook) {

            val newBook = Book(
                title = etTitle.text.toString(),
                author = etAuthor.text.toString(),
                publishDate = Helpers.stringToDate(etDate.text.toString()),
                isbn = etISBN.text.toString(),
                read = cbRead.isChecked
            )

            if (bookDetailsAreFilled(newBook)) {
                bookRepository.addBook(newBook) { returnToMain() }
                return true
            } else {
                titleInputLayout.isErrorEnabled = true
                titleInputLayout.error = getString(R.string.enter_book_title)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun returnToMain() {
        setResult(Activity.RESULT_OK, getResultIntent())
        finish()
    }

    private fun bookDetailsAreFilled(book: Book): Boolean {
        if (book.title.isEmpty())
            return false

        return true
    }

    companion object {

        fun getResultIntent() =
            Intent().also {
                it.putExtra(RESULT_NEW_BOOK, ADD_NEW_BOOK)
            }

        fun getIntent(context: Context): Intent {
            return Intent(context, AddBookActivity::class.java)
        }

        private const val ADD_NEW_BOOK = 10
        private const val RESULT_NEW_BOOK = "ADDED_BOOK"
    }

    private fun initializeDatePicker(): DatePickerDialog {
        return DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                etDate.setText(sdf.format(calendar.time))
            },
            year,
            month,
            day
        )
    }
}
