package io.bloco.biblioteca.testhelpers

import io.bloco.biblioteca.Book

object BookFactory {

    fun makeBook(title: String = "Harry Potter") =
        Book(
            title,
            "JK Rowling",
            "23/05/15",
            "12345",
            false
        )
}
