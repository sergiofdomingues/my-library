package io.bloco.biblioteca

import android.app.Application
import android.os.StrictMode

@Suppress("unused")
class App : Application() {

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
}