package io.bloco.biblioteca.helpers

import io.bloco.biblioteca.model.Book
import java.util.*

class Validation {

    fun validateBook(book: Book): List<ValidationErrors> {
        val errorList = mutableListOf<ValidationErrors>()

        // Check title
        if (bookTitleIsNotFilled(book.title)) {
            errorList.add(ValidationErrors.TITLE_INVALID)
        }
        // Check isbn
        if (bookIsbnIsNotValid(book.isbn)) {
            errorList.add(ValidationErrors.ISBN_INVALID)
        }
        // Check date
        if (bookDateIsNotValid(book.publishDate)) {
            errorList.add(ValidationErrors.DATE_INVALID)
        }

        return errorList
    }


    private fun bookTitleIsNotFilled(bookTitle: String): Boolean {
        return bookTitle.isEmpty()
    }

    private fun bookIsbnIsNotValid(bookIsbn: String?): Boolean {
        if (bookIsbn!!.isEmpty())
            return false
        return bookIsbn.length != ISBN_DIGITS
    }

    private fun bookDateIsNotValid(bookDate: Date?): Boolean {
        if (bookDate == null)
            return false

        val current = Date()
        return bookDate.time >= current.time
    }

    companion object {
        private const val ISBN_DIGITS = 13
    }
}