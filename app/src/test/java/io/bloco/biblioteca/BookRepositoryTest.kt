package io.bloco.biblioteca

import io.bloco.biblioteca.database.BookRepository
import io.bloco.biblioteca.testhelpers.BookFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject

class BookRepositoryTest {

    @Inject lateinit var repository: BookRepository
    private val bookFactory = BookFactory

    @Test
    fun addBookTest() {
        repository.deleteAllBooksInDb()
        assertEquals(repository.getNumBooks(), 0)
        repository.addBook(bookFactory.makeCompleteBook())
        waitForAddBookCallBack(1)
        assertEquals(repository.getNumBooks(), 1)
    }

    @Test
    fun removeTest() {
        repository.deleteAllBooksInDb()
        val book = bookFactory.makeCompleteBook()
        repository.addBook(book) {}
        waitForAddBookCallBack(1)
        assertEquals(repository.getNumBooks(), 1)
        val book2 = bookFactory.makeCompleteBook("other")
        repository.addBook(book2) {}
        waitForAddBookCallBack(2)
        assertEquals(repository.getNumBooks(), 2)
        repository.deleteBookTesting(book2)
        waitForAddBookCallBack(1)
        assertEquals(repository.getNumBooks(), 1)
    }

    // Helpers

    private fun waitForAddBookCallBack(expectedBooks: Int) {
        var threshold = 1
        while (repository.getNumBooks() != expectedBooks && threshold < 10) {
            Thread.sleep(100)
            threshold++
        }
    }
}