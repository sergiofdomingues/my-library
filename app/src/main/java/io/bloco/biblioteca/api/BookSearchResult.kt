package io.bloco.biblioteca.api

import java.lang.Exception

sealed class BookSearchResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : BookSearchResult<T>()
    data class Error(val exception: Exception) : BookSearchResult<Nothing>()
}