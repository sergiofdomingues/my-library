package io.bloco.biblioteca.helpers

import io.bloco.biblioteca.Book
import java.util.*

class Validation {

    fun validateBook(book: Book): List<String> {
        val errorList = mutableListOf<String>()

        // Check title
        if (bookTitleIsNotFilled(book.title)) {
            errorList.add(ValidationErrors.TITLEINVALID.name)
        }

        // Check isbn
        if (bookIsbnIsNotValid(book.isbn)) {
            errorList.add(ValidationErrors.ISBNINVALID.name)
        }

        // Check date
        if (bookDateIsNotValid(book.publishDate)) {
            errorList.add(ValidationErrors.DATEINVALID.name)
        }

        return errorList
    }


    private fun bookTitleIsNotFilled(bookTitle: String): Boolean {
        if (bookTitle.isEmpty())
            return true
        return false
    }

    private fun bookIsbnIsNotValid(bookIsbn: String?): Boolean {
        if (bookIsbn!!.isEmpty())
            return false
        if (bookIsbn.length != 13)
            return true
        return false
    }

    private fun bookDateIsNotValid(bookDate: Date?): Boolean {
        if (bookDate == null)
            return false

        val current = Date()
        if ((bookDate.time) >= current.time)
            return true
        return false
    }
}