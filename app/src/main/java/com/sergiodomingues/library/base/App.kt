package com.sergiodomingues.library.base

import android.app.Application
import android.os.StrictMode
import com.sergiodomingues.library.common.di.ApplicationComponent
import com.sergiodomingues.library.common.di.ApplicationModule
import com.sergiodomingues.library.common.di.DaggerApplicationComponent

@Suppress("unused")
class App : Application() {

    var mode = Mode.NORMAL
    //private val api by lazy { ApiCaller(RetrofitInstance().getClient().create(ApiInterface::class.java)) }
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(
                ApplicationModule(
                    this
                )
            )
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        strictMode()
        checkTestMode()
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
            classLoader.loadClass("com.sergiodomingues.library.AllActivitiesTest")
            Mode.TEST
        } catch (e: Exception) {
            Mode.NORMAL
        }
    }

    enum class Mode {
        NORMAL, TEST
    }
}