package io.bloco.biblioteca.testhelpers

import io.bloco.biblioteca.helpers.DateHelpers.stringToDate
import io.bloco.biblioteca.model.Book
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
