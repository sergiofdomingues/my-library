package io.bloco.biblioteca

import io.bloco.biblioteca.helpers.Validation
import io.bloco.biblioteca.helpers.ValidationErrors
import io.bloco.biblioteca.testhelpers.BookFactory.makeIncompleteBook
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class ValidationTest {

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    @Before
    fun setup() {
        setValidDate()
    }

    @Test
    fun validateBookTitle() {
        var errorList: List<ValidationErrors>?
        var book: Book?
        val validator = Validation()

        setValidDate()
        book = makeIncompleteBook(INVALID_TITLE, AUTHOR, calendar.time, VALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 1)
        assertEquals(errorList[0], ValidationErrors.TITLE_INVALID)

        book = makeIncompleteBook(VALID_TITLE, AUTHOR, calendar.time, VALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 0)
    }

    @Test
    fun validateBookIsbn() {

        var errorList: List<ValidationErrors>?
        var book: Book?
        val validator = Validation()

        setValidDate()
        book = makeIncompleteBook(VALID_TITLE, AUTHOR, calendar.time, INVALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 1)
        assertEquals(errorList[0], ValidationErrors.ISBN_INVALID)

        book = makeIncompleteBook(VALID_TITLE, AUTHOR, calendar.time, VALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 0)
    }

    @Test
    fun validateBookDate() {

        var errorList: List<ValidationErrors>?
        var book: Book?
        val validator = Validation()

        // Invalid book
        setInvalidDate()
        book = makeIncompleteBook(VALID_TITLE, AUTHOR, calendar.time, VALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 1)
        assertEquals(errorList[0], ValidationErrors.DATE_INVALID)

        // Valid book
        setValidDate()
        book = makeIncompleteBook(VALID_TITLE, AUTHOR, calendar.time, VALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 0)
    }

    @Test
    fun validateMultipleInvalid() {

        var errorList: List<ValidationErrors>?
        var book: Book?
        val validator = Validation()

        setInvalidDate()
        book = makeIncompleteBook(INVALID_TITLE, AUTHOR, calendar.time, VALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 2)
        assert(
            errorList.containsAll(
                listOf(
                    ValidationErrors.TITLE_INVALID,
                    ValidationErrors.DATE_INVALID
                )
            )
        )

        setValidDate()
        book = makeIncompleteBook(
            INVALID_TITLE, AUTHOR, calendar.time,
            INVALID_ISBN, READ
        )
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 2)
        assert(
            errorList.containsAll(
                listOf(
                    ValidationErrors.TITLE_INVALID,
                    ValidationErrors.ISBN_INVALID
                )
            )
        )

        book = makeIncompleteBook(VALID_TITLE, AUTHOR, calendar.time, INVALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 1)
        assert(errorList.contains(ValidationErrors.ISBN_INVALID))

        setInvalidDate()
        book = makeIncompleteBook(INVALID_TITLE, AUTHOR, calendar.time, INVALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assertEquals(errorList.size, 3)
        assert(
            errorList.containsAll(
                listOf(
                    ValidationErrors.DATE_INVALID,
                    ValidationErrors.ISBN_INVALID, ValidationErrors.TITLE_INVALID
                )
            )
        )

        setValidDate()
        book = makeIncompleteBook(VALID_TITLE, AUTHOR, calendar.time, VALID_ISBN, READ)
        errorList = validator.validateBook(book)
        assert(errorList.isEmpty())
    }

    companion object {
        private const val VALID_TITLE = "Harry Potter"
        private const val INVALID_TITLE = ""
        private const val AUTHOR = "J. K. Rowling"
        private const val VALID_ISBN = "1234567890123"
        private const val INVALID_ISBN = "123456789012"
        private const val READ = false
    }

    private fun setValidDate() {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day - 1)
    }

    private fun setInvalidDate() {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day + 1)
    }
}