package com.sergiodomingues.library.testhelpers

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

object InstrumentationContext {

    fun useContext(): Context {
        return InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    }
}