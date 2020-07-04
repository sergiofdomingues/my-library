package com.sergiodomingues.library

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sergiodomingues.library.testhelpers.BookFactory
import org.junit.Assert.assertEquals
import com.sergiodomingues.library.testhelpers.AppHelper.app
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BookRepositoryInstrumentedTest {
/*
    private val bookFactory = BookFactory
    private var repository = app.component.bookRepository()
    //private val repository = (InstrumentationContext.useContext() as App).getBookRepository()

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
    }*/
}