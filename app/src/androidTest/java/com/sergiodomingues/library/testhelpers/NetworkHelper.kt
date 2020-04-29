package com.sergiodomingues.library.testhelpers

import com.sergiodomingues.library.api.ApiInterface
import com.sergiodomingues.library.api.MockInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    fun provideApi(): ApiInterface {
        return provideRetrofitDebug()
            .create(ApiInterface::class.java)
    }

    private fun provideRetrofitDebug(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpTestClient())
            .build()
    }

    private fun provideHttpTestClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(MockInterceptor())
        .build()

}