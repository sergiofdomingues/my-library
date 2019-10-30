package io.bloco.biblioteca

import io.bloco.biblioteca.testhelpers.BookFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BookRepositoryTest {
    private val repository = BookRepository
    private val bookFactory = BookFactory

    @Before
    fun setUp() { repository.clearRepository() }

    @Test
    fun addBookTest() {
        assertEquals(repository.books.size, 0)
        repository.addBook(bookFactory.makeBook())
        assertEquals(repository.books.size, 1)
    }

    @Test
    fun removeTest() {
        val book = bookFactory.makeBook()
        repository.addBook(book)
        val book2 = bookFactory.makeBook("outro")
        repository.addBook(book2)
        repository.deleteBookByTitle(book.title)
        assertEquals(repository.books.size, 1)
    }
}