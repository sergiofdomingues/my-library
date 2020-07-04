package com.sergiodomingues.library.booksearch

import com.jakewharton.rxrelay2.BehaviorRelay
import com.sergiodomingues.library.base.viewmodel.BaseViewModel
import com.sergiodomingues.library.booksearch.usecase.SearchBooks
import com.sergiodomingues.library.model.FoundBook
import com.sergiodomingues.library.util.Operation
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchBookViewModel @Inject constructor(
    private val searchBooks: SearchBooks
) : BaseViewModel() {

    private val queries = BehaviorRelay.create<String>()

    private val refreshBooks = BehaviorRelay.create<List<FoundBook>>()
    private val errors = BehaviorRelay.create<Error>()

    init {

        queries
            .map { it.trim() }
            .filter { it.isEmpty() }
            .throttleFirst(THROTTLE_DURATION, TimeUnit.MILLISECONDS)
            .subscribe {
                refreshBooks.accept(emptyList())
            }
            .addTo(disposables)

        queries
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .throttleFirst(THROTTLE_DURATION, TimeUnit.MILLISECONDS)
            .flatMapSingle {
                searchBooks.search(it)
            }
            .subscribe {
                when (it) {
                    is Operation.Success<List<FoundBook>> -> refreshBooks.accept(it.result)
                    is Operation.Error<List<FoundBook>> -> errors.accept(Error.ErrorSearching)
                }
            }
            .addTo(disposables)
    }

    // Input

    fun searchQueryChanged(query: String) = queries.accept(query)

    // Output

    fun refreshBooks() = refreshBooks.hide()
    fun errors() = errors.hide()

    companion object {
        private const val THROTTLE_DURATION = 500L
    }

    enum class Error {
        ErrorSearching
    }
}