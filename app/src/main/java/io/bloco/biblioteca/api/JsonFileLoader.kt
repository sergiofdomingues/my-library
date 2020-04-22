package io.bloco.biblioteca.api

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class JsonFileLoader {

    fun loadFile(fileName: String) : String {
        try {
            val inStream = Objects.requireNonNull(JsonFileLoader::class.java.classLoader)?.getResourceAsStream(fileName)
            inStream?.let {
                val reader = BufferedReader(InputStreamReader(inStream))
                val total = StringBuilder()
                var line: String?
                line = reader.readLine()
                while (line != null) {
                    total.append(line).append('\n')
                    line = reader.readLine()
                }
                return total.toString()
            }
            throw Exception("InputStream is null")
        } catch (ex: IOException) {
            throw RuntimeException(ex)
        }
    }
}