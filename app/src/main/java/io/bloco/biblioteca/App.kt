package io.bloco.biblioteca

import android.app.Application
import android.os.StrictMode
import io.bloco.biblioteca.common.di.ApplicationComponent
import io.bloco.biblioteca.common.di.ApplicationModule
import io.bloco.biblioteca.common.di.DaggerApplicationComponent
import io.bloco.biblioteca.database.AppDatabase
import io.bloco.biblioteca.database.BookRepository

@Suppress("unused")
class App : Application() {

    var mode = Mode.NORMAL
    //private val api by lazy { ApiCaller(RetrofitInstance().getClient().create(ApiInterface::class.java)) }
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        strictMode()
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