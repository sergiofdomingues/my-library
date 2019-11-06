package io.bloco.biblioteca

import android.app.Application
import android.os.StrictMode
import io.bloco.biblioteca.database.AppDatabase

@Suppress("unused")
class App : Application() {

    private val db by lazy { AppDatabase.getDatabase(this) }
    //var testing = false
    var mode = Mode.NORMAL

    override fun onCreate() {
        super.onCreate()
        //strictMode()
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