package io.bloco.biblioteca

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import io.bloco.biblioteca.helpers.Helpers.dateToString
import kotlinx.android.synthetic.main.activity_book_info.*
import timber.log.Timber

class BookInfoActivity : AppCompatActivity() {

    private var selectedBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)

        selectedBook = intent.getParcelableExtra(BOOK_KEY)
        selectedBook?.let {
            initDetailFields(it)
        }
        requestExernalStorageReadPermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (permissions.isEmpty()) {
                    Timber.i(getString(R.string.perm_denied))
                } else {
                    Timber.i(getString(R.string.perm_granted))
                    loadImageFromDevice()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun initDetailFields(selectedBook: Book) {
        tvBookTitle.text = selectedBook.title
        tvBookAuthor.text = selectedBook.author ?: ""
        tvBookIsbn.text = selectedBook.isbn ?: ""
        tvBookDate.text = dateToString(selectedBook.publishDate)
    }

    private fun requestExernalStorageReadPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {

                requestPermissions(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    RECORD_REQUEST_CODE
                )
                return
            } else
                loadImageFromDevice()
        }
    }

    private fun loadImageFromDevice() {
        selectedBook?.uriCover?.let {
            Glide.with(this).load(selectedBook?.uriCover).into(ivBookCover)
        }
    }

    companion object {
        private const val BOOK_KEY = "BOOK"
        private const val RECORD_REQUEST_CODE = 101

        fun getIntent(context: Context, book: Book) =
            Intent(context, BookInfoActivity::class.java).also {
                it.putExtra(BOOK_KEY, book)
            }
    }
}
