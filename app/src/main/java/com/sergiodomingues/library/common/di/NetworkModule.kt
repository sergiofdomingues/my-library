package com.sergiodomingues.library.common.di

import android.content.res.Resources
import com.sergiodomingues.library.R
import com.sergiodomingues.library.api.ApiInterface
import com.sergiodomingues.library.api.MockInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(resources: Resources): ApiInterface =
        provideRetrofit(resources).create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(resources: Resources): Retrofit =
        Retrofit.Builder()
            .baseUrl(resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient())
            .build()

    fun provideRetrofitDebug(resources: Resources): Retrofit =
        Retrofit.Builder()
            .baseUrl(resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpTestClient())
            .build()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpTestClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(MockInterceptor())
        .build()
}