package com.sergiodomingues.library

import com.sergiodomingues.library.api.JsonFileLoader
import org.junit.Assert.assertNotNull
import org.junit.Test

class JsonFileLoaderTest {
    private val loader = JsonFileLoader()

    @Test
    fun validateLoadedJsonFile() {
        val strJson = loader.loadFile(JSON_RESPONSE_FILE)
        assertNotNull(strJson)
    }

    companion object {
        private const val JSON_RESPONSE_FILE = "book_search_response.json"
    }
}