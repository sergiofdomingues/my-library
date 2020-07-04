package com.sergiodomingues.library.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchBooksService {

    @GET("volumes/{isbn}")
    fun getBookByIsbn(
        @Path("isbn") bookIsbn: String,
        @Query("API_KEY") apiKey: String
    ): Single<BookResponse>

    @GET("volumes")
    fun getBookByQuery(
        @Query("q") query: String,
        @Query("API_KEY") apiKey: String
    ): Single<BookResponse>
}