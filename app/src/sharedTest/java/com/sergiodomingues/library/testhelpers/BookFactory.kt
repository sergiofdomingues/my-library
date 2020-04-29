package com.sergiodomingues.library.testhelpers

import com.sergiodomingues.library.helpers.DateHelpers.stringToDate
import com.sergiodomingues.library.model.Book
import java.util.*

object BookFactory {

    fun makeIncompleteBook(
        title: String, author: String?,
        publishDate: Date? = stringToDate(), isbn: String?, read: Boolean
    ) =
        Book(
            title = title,
            author = author,
            publishDate = publishDate,
            isbn = isbn,
            read = read
        )

    fun makeCompleteBook(title: String = "Harry Potter") =
        Book(
            title = title,
            author = "JK Rowling",
            publishDate = stringToDate("07/10/2012"),
            isbn = "1234567891234",
            read = true
        )
}