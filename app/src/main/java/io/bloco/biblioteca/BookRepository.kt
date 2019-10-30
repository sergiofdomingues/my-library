package io.bloco.biblioteca

import androidx.annotation.VisibleForTesting

object BookRepository {

    private val memoryDataBase by lazy { mutableListOf<Book>() }

    val books: List<Book> get() = memoryDataBase.toList()

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

    fun addBook(newBook: Book) {
        memoryDataBase.add(newBook)
    }

    fun deleteBook(book: Book) {
        memoryDataBase.remove(book)
    }

    @VisibleForTesting
    fun clearRepository() {
        memoryDataBase.clear()
    }

    fun deleteBookByTitle(title: String) {
        for (i in 0 until memoryDataBase.size - 1) {
            if (memoryDataBase[i].title == title) {
                memoryDataBase.removeAt(i)
                return
            }
        }
    }

}