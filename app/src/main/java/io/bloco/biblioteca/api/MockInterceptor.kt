package io.bloco.biblioteca.api

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
            .newBuilder()
            .body(
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    JsonFileLoader().loadFile(JSON_RESPONSE_FILE).toByteArray()
                )
            )
            .build()
    }

    companion object {
        private const val JSON_RESPONSE_FILE = "book_search_response.json"
    }
}