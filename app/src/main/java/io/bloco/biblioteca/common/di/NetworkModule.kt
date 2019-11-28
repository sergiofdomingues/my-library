package io.bloco.biblioteca.common.di

import dagger.Module
import dagger.Provides
import io.bloco.biblioteca.api.ApiInterface
import io.bloco.biblioteca.api.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): ApiInterface {
        return provideRetrofit().create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitDebug(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpTestClient())
            .build()
    }

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

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }

}