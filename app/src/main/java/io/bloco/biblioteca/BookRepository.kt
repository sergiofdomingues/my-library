package io.bloco.biblioteca

import androidx.annotation.VisibleForTesting
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

object BookRepository {

    private val memoryDataBase by lazy { mutableListOf<Book>() }

    //val books: List<Book> get() = memoryDataBase.toList()

    fun getBooks(onComplete: ((List<Book>) -> Unit)) {
        doAsync {
            val books = memoryDataBase.toList()
            uiThread {
                onComplete.invoke(books)
            }
        }
    }

    fun addBook(newBook: Book, onCompete: (() -> Unit)) {
        doAsync {
            memoryDataBase.add(newBook)
            uiThread {
                onCompete.invoke()
            }
        }

    }

    fun deleteBook(book: Book, onComplete: (() -> Unit)? = null) {
        doAsync {
            memoryDataBase.remove(book)
            uiThread {
                onComplete?.invoke()
            }
        }
    }

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
                    "Harry Potter e a Pedra Filosofal",
                    "J. K. Rowling",
                    "29/10/15",
                    "190-456-566",
                    true
                ),
                Book(
                    "O livro do desassossego",
                    "Fernando Pessoa",
                    "01/02/12",
                    "191-456-566",
                    false
                ),
                Book("War and Peace", "Leo Tolstoy", "", "192-456-566", false),
                Book("Hamlet", "William Shakespeare", "14/07/09", "193-456-566", false)
            )
        )
    }
}