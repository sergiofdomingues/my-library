package io.bloco.biblioteca.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("volumes/{isbn}")
    fun getBookByIsbn(@Path("isbn") bookIsbn: String,
    @Query("API_KEY") apiKey: String) : Call<BookResponse>

    @GET("volumes")
    fun getBookByQuery(@Query("q") query: String,
                       @Query("API_KEY") apiKey: String) : Call<BookResponse>

}