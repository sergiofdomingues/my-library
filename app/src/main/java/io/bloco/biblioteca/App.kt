package io.bloco.biblioteca

import android.app.Application
import android.os.StrictMode
import io.bloco.biblioteca.database.AppDatabase

@Suppress("unused")
class App : Application() {

    private val db by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        //strictMode()
    }

    fun getBookRepository() : BookRepository {
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
}