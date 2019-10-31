package io.bloco.biblioteca

import android.util.Log
import androidx.annotation.VisibleForTesting
import io.bloco.biblioteca.helpers.Helpers.stringToDate
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber

class BookRepository(private val bookDao: BookDao) {

    private val memoryDataBase by lazy { mutableListOf<Book>() }

    fun getBooks(onComplete: ((List<Book>) -> Unit)) {
        doAsync {
            //val books = memoryDataBase.toList()
            val books = bookDao.getAllBooks()
            uiThread {
                onComplete.invoke(books)
            }
        }
    }

    fun addBook(newBook: Book, onCompete: (() -> Unit)? = null) {
        doAsync {
            //memoryDataBase.add(newBook)
            try {
                bookDao.insertBook(newBook)
            } catch (e: Exception) {
                Log.d("Exception ", e.toString())
            }
            uiThread {
                onCompete?.invoke()
            }
        }

    }

    fun deleteBook(book: Book, onComplete: (() -> Unit)? = null) {
        doAsync {
            //memoryDataBase.remove(book)
            bookDao.deleteBookById(book.id)
            uiThread {
                onComplete?.invoke()
            }
        }
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

    // Volatile database stuff

    @VisibleForTesting
    fun size(): Int {
        return memoryDataBase.size
    }

    @VisibleForTesting
    fun clearRepository() {
        memoryDataBase.clear()
    }

    @VisibleForTesting
    fun deleteBookByTitle(title: String) {
        for (i in 0 until memoryDataBase.size - 1) {
            if (memoryDataBase[i].title == title) {
                memoryDataBase.removeAt(i)
                return
            }
        }
    }

    @Suppress("unused")
    fun initBooks() {
        memoryDataBase.addAll(
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