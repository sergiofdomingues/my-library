package io.bloco.biblioteca.testhelpers

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import io.bloco.biblioteca.base.App

object AppHelper {
    val context: Context get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    val app get() = context as App
    val appComponent get() = app.component
}