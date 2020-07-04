package com.sergiodomingues.library

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sergiodomingues.library.api.BookResponse
import com.sergiodomingues.library.api.JsonFileLoader
import com.sergiodomingues.library.api.SearchBooksService
import com.sergiodomingues.library.booksearch.usecase.SearchBooks
import com.sergiodomingues.library.common.di.GsonModule
import com.sergiodomingues.library.util.Operation
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class SearchBooksTest {
    private val gson by lazy { GsonModule().gson() }
    private val searchBooksService = mock<SearchBooksService>()
    private val subject = SearchBooks(searchBooksService)

    @Test
    fun validateApiResponse() {
        val bookResponse: BookResponse =
            gson.fromJson<BookResponse>(
                JsonFileLoader.loadResponse("book_search_response"),
                BookResponse::class.java
            )
        whenever(searchBooksService.getBookByQuery(anyString(), anyString()))
            .thenReturn(Single.just(bookResponse))

        val books = subject.search("anyBook").blockingGet()
        assertThat(books).isInstanceOf(Operation.Success::class.java)
        (books as Operation.Success).result.run {
            assertThat(this.size == 10)
            assertThat(this[0].title).isEqualTo(FIRST_BOOK_TITLE)
            assertThat(this[9].title).isEqualTo(LAST_BOOK_TITLE)
        }
    }

    companion object {
        const val FIRST_BOOK_TITLE = "Harry Potter e a Pedra Filosofal"
        const val LAST_BOOK_TITLE = "Harry Potter i les relíquies de la mort"
    }
}