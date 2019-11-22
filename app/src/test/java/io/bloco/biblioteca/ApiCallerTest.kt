package io.bloco.biblioteca

import io.bloco.biblioteca.api.ApiCaller
import io.bloco.biblioteca.api.ApiInterface
import io.bloco.biblioteca.api.RetrofitInstance
import io.bloco.biblioteca.model.FoundBook
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiCallerTest {
    private val api =
        ApiCaller(RetrofitInstance().getDebugClient()?.create(ApiInterface::class.java))
    private val foundBooksList = mutableListOf<FoundBook>()

    @Test
    fun validateApiResponse() {
        api.performSearchByQuery(HARRY_POTTER_BOOK) { receiveUpdatedFoundBooksList(it) }
        waitForCallBack()
        assertEquals(foundBooksList[0].title, FIRST_BOOK_TITLE)
        assertEquals(foundBooksList.size, 10)
    }

    private fun receiveUpdatedFoundBooksList(updatedList: List<FoundBook>) {
        foundBooksList.clear()
        foundBooksList.addAll(updatedList)
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