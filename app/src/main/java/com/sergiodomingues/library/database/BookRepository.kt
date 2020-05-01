package com.sergiodomingues.library.database

import androidx.annotation.VisibleForTesting
import com.sergiodomingues.library.model.Book
import com.sergiodomingues.library.util.toOperation
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import javax.inject.Inject

class BookRepository @Inject constructor(private val bookDao: BookDao) {

    fun getBooks() =
        bookDao.getAllBooks()
            .toOperation()

    fun addBook(newBook: Book, onComplete: (() -> Unit)? = null) {
        doAsync {
            try {
                bookDao.insertBook(newBook)
            } catch (e: Exception) {
                Timber.d("ExceptionAddingBook%s", e.toString())
            }
            uiThread {
                onComplete?.invoke()
            }
        }
    }

    fun deleteBook(id: Long) =
        bookDao.deleteBookById(id)
            .toOperation()

    fun getCountBooks(onComplete: ((Int) -> Unit)? = null) {
        doAsync {
            val booksCount = bookDao.countBooks()
            uiThread {
                onComplete?.invoke(booksCount)
            }
        }
    }

    fun deleteAllBooks(onComplete: (() -> Unit)? = null) {
        doAsync {
            bookDao.deleteAllBooks()
            uiThread {
                onComplete?.invoke()
            }
        }
    }

    @VisibleForTesting
    fun deleteBookTesting(book: Book) {
        bookDao.deleteBookByTitle(book.title)
    }

    @VisibleForTesting
    fun getNumBooks(): Int {
        return bookDao.countBooks()
    }

    fun deleteAllBooksInDb() {
        bookDao.deleteAllBooks()
    }

/*    fun initBooksInDb() {
        doAsync {
            if (bookDao.getAllBooks().isNotEmpty()) return@doAsync
            bookDao.insertMultipleBooks(
                listOf(
                    Book(
                        title = "Harry Potter e a Pedra Filosofal",
                        author = "J. K. Rowling",
                        publishDate = stringToDate("29/10/15"),
                        isbn = "190-456-566",
                        read = true
                    ),
                    Book(
                        title = "O livro do desassossego",
                        author = "Fernando Pessoa",
                        publishDate = stringToDate("01/02/12"),
                        isbn = "191-456-566",
                        read = false
                    ),
                    Book(
                        title = "War and Peace",
                        author = "Leo Tolstoy",
                        publishDate = stringToDate(),
                        isbn = "192-456-566",
                        read = false
                    ),
                    Book(
                        title = "Hamlet",
                        author = "William Shakespeare",
                        publishDate = stringToDate("14/07/09"),
                        isbn = "193-456-566",
                        read = false
                    )
                )
            )
        }
    }*/
}