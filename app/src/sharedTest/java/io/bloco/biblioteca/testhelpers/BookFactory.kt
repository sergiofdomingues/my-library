package io.bloco.biblioteca.testhelpers

import io.bloco.biblioteca.Book
import io.bloco.biblioteca.helpers.Helpers.stringToDate

object BookFactory {

    fun makeBook(title: String = "Harry Potter") =
        Book(
            title = title,
            author = "JK Rowling",
            publishDate = stringToDate("23/05/15"),
            isbn = "12345",
            read = false
        )
}
