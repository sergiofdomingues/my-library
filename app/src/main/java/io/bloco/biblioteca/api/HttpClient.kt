package io.bloco.biblioteca.api

import okhttp3.OkHttpClient

class HttpClient {
    fun provideHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(MockInterceptor())
        .build()
}