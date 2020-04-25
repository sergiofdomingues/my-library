package io.bloco.biblioteca

import io.bloco.biblioteca.api.ApiCaller
import io.bloco.biblioteca.api.BookSearchResult
import io.bloco.biblioteca.model.FoundBook
import io.bloco.biblioteca.testhelpers.NetworkHelper.provideApi
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiCallerTest {
    private var api = ApiCaller(provideApi())
    //private val api = ApiCaller(RetrofitInstance().getDebugClient().create(ApiInterface::class.java))

    private val foundBooksList = mutableListOf<FoundBook>()

    @Test
    fun validateApiResponse() {
        api.performSearchByQuery(HARRY_POTTER_BOOK) { receiveUpdatedFoundBooksList(it) }
        waitForCallBack()
        assertEquals(foundBooksList[0].title, FIRST_BOOK_TITLE)
        assertEquals(foundBooksList.size, 10)
    }

    private fun receiveUpdatedFoundBooksList(response: (BookSearchResult<Any>)) {
        when (response) {
            is BookSearchResult.Success -> {
                foundBooksList.clear()
                foundBooksList.addAll(response.data as List<FoundBook>)
            }
        }
    }

    private fun waitForCallBack() {
        var time = 0
        while (foundBooksList.size < 10 && time < 10) {
            Thread.sleep(500)
            time++
        }
    }

    companion object {
        const val HARRY_POTTER_BOOK = "HarryPotter"
        const val FIRST_BOOK_TITLE = "Harry Potter e a Pedra Filosofal"
    }
}