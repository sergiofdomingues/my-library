package com.sergiodomingues.library.helpers

import com.sergiodomingues.library.model.Book
import java.util.*
import javax.inject.Inject

class BookValidator @Inject constructor() {

    fun validate(book: Book): List<ValidationErrors> {
        val errorList = mutableListOf<ValidationErrors>()

        // Check title
        if (bookTitleIsNotFilled(book.title)) {
            errorList.add(ValidationErrors.TITLE_INVALID)
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


    private fun bookDateIsNotValid(bookDate: Date?): Boolean {
        if (bookDate == null)
            return false

        val current = Date()
        return bookDate.time >= current.time
    }
}