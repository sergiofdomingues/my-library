package com.sergiodomingues.library.testhelpers

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.sergiodomingues.library.base.App

object AppHelper {
    val context: Context get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    val app get() = context as App
    val appComponent get() = app.component
}