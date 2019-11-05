package io.bloco.biblioteca

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.bloco.biblioteca.testhelpers.BookFactory
import io.bloco.biblioteca.testhelpers.TestHelpers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BookRepositoryInstrumentedTest {

    private val bookFactory = BookFactory
    private val repository = (TestHelpers.useContext() as App).getBookRepository()

    @Before
    fun setUp() {
        (TestHelpers.useContext() as App).testing = true
    }

    @Test
    fun addBookTest() {
        repository.deleteAllBooksInDb()
        assertEquals(repository.getNumBooks(), 0)
        repository.addBook(bookFactory.makeBook()) {}
        waitForAddBookCallBack(1)
        assertEquals(repository.getNumBooks(), 1)
    }

    @Test
    fun removeTest() {
        repository.deleteAllBooksInDb()
        val book = bookFactory.makeBook()
        repository.addBook(book) {}
        waitForAddBookCallBack(1)
        assertEquals(repository.getNumBooks(), 1)
        val book2 = bookFactory.makeBook("other")
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