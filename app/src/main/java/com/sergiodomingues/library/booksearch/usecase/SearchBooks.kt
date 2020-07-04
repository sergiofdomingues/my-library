package com.sergiodomingues.library.booksearch.usecase

import com.sergiodomingues.library.api.SearchBooksService
import com.sergiodomingues.library.util.toOperation
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchBooks @Inject constructor(
    private val searchBooksService: SearchBooksService
) {
    fun search(query: String) =
        searchBooksService.getBookByQuery(query, API_KEY)
            .subscribeOn(Schedulers.io())
            .map { it.toModel() }
            .toOperation()

    companion object {
        private const val API_KEY = "AIzaSyDV_QELWETLCzg8iHZB7w2_gBwT1VCmnIo"
    }
}