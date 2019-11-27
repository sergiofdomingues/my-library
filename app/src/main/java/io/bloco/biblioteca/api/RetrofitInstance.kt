package io.bloco.biblioteca.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(HttpClient().provideHttpClient())
            .build()
    }

    fun getDebugClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(HttpClient().provideHttpTestClient())
            .build()
    }

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}

