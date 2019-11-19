package io.bloco.biblioteca

import android.app.Application
import android.os.StrictMode
import io.bloco.biblioteca.database.AppDatabase
<<<<<<< Updated upstream:app/src/main/java/io/bloco/biblioteca/App.kt
=======
import io.bloco.biblioteca.database.repository.BookRepository
import io.bloco.biblioteca.rest.ApiCaller
import io.bloco.biblioteca.rest.ApiInterface
import io.bloco.biblioteca.rest.RetrofitInstance
>>>>>>> Stashed changes:app/src/main/java/io/bloco/biblioteca/app/App.kt

@Suppress("unused")
class App : Application() {

    private val db by lazy { AppDatabase.getDatabase(this) }
    //var testing = false
    var mode = Mode.NORMAL
    private val api by lazy { ApiCaller(RetrofitInstance().getClient()?.create(ApiInterface::class.java)) }

    override fun onCreate() {
        super.onCreate()
        //strictMode()
    }

    fun getApiCaller(): ApiCaller {
        return api
    }

    fun getBookRepository(): BookRepository {
        return BookRepository(db.bookDao())
    }

    private fun strictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .build()
        )
    }

    // Test loading a random test class, to check if we're in test mode
    private fun checkTestMode() {
        mode = try {
            classLoader.loadClass("io.bloco.biblioteca.MainActivityTest")
            Mode.TEST
        } catch (e: Exception) {
            Mode.NORMAL
        }
    }

    enum class Mode {
        NORMAL, TEST
    }
}