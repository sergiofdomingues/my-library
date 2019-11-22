package io.bloco.biblioteca.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun getDebugClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpClient().provideHttpClient()) //testing
                .build()
        }
        return retrofit
    }


    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}

