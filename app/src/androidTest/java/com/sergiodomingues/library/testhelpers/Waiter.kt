package com.sergiodomingues.library.testhelpers

import com.sergiodomingues.library.database.BookRepository

object Waiter {

    fun waitForAddBookCallBack(expectedBooks: Int, repository: BookRepository) {
        var threshold = 1
        while (repository.getNumBooks() != expectedBooks && threshold < 10) {
            Thread.sleep(100)
            threshold++
        }
    }
}