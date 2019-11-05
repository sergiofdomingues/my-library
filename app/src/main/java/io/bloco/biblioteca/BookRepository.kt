package io.bloco.biblioteca

import androidx.annotation.VisibleForTesting
import io.bloco.biblioteca.helpers.Helpers.stringToDate
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber

class BookRepository(private val bookDao: BookDao) {

    fun getBooks(onComplete: ((List<Book>) -> Unit)) {
        doAsync {
            //val books = memoryDataBase.toList()
            val books = bookDao.getAllBooks()
            uiThread {
                onComplete.invoke(books)
            }
        }
    }

    fun getCountBooks(onComplete: ((Int) -> Unit)? = null) {
        doAsync {
            val booksCount = bookDao.countBooks()
            uiThread {
                onComplete?.invoke(booksCount)
            }
        }
    }

    fun addBook(newBook: Book, onCompete: (() -> Unit)? = null) {
        doAsync {
            //memoryDataBase.add(newBook)
            try {
                bookDao.insertBook(newBook)
            } catch (e: Exception) {
                Timber.d("ExceptionAddingBook%s", e.toString())
            }
            uiThread {
                onCompete?.invoke()
            }
        }

    }

    fun deleteBook(book: Book, onComplete: (() -> Unit)? = null) {
        doAsync {
            bookDao.deleteBookById(book.id)
            uiThread {
                onComplete?.invoke()
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

    fun initBooksInDb() {
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
    }
}