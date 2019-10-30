package io.bloco.biblioteca

import io.bloco.biblioteca.testhelpers.BookFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BookRepositoryTest {
    private val repository = BookRepository
    private val bookFactory = BookFactory

    @Before
    fun setUp() {
        repository.clearRepository()
    }

    @Test
    fun addBookTest() {
        assertEquals(repository.size(), 0)
        repository.addBook(bookFactory.makeBook()) {}
        waitForAddBookCallBack(1)
        assertEquals(repository.size(), 1)
    }

    @Test
    fun removeTest() {
        val book = bookFactory.makeBook()
        repository.addBook(book) {}
        waitForAddBookCallBack(1)
        val book2 = bookFactory.makeBook("outro")
        repository.addBook(book2) {}
        waitForAddBookCallBack(2)
        repository.deleteBookByTitle(book.title)
        assertEquals(repository.size(), 1)
    }

    // Helpers

    private fun waitForAddBookCallBack(expectedBooks: Int) {
        var counter = 1
        while (repository.size() != expectedBooks && counter < 10) {
            Thread.sleep(100)
            counter++
        }
    }
}