package com.sergiodomingues.library.testhelpers

import android.app.Activity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.junit.Assert

object ActivityAsserter {

    private val currentActivity: Activity?
        get() {
            InstrumentationRegistry.getInstrumentation().waitForIdleSync()
            val activity = arrayOfNulls<Activity>(1)
            InstrumentationRegistry.getInstrumentation().runOnMainSync {
                val activities =
                    ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(
                        Stage.RESUMED
                    )
                activity[0] = activities.iterator().next()
            }
            return activity[0]
        }

    fun assertCurrentActivity(activityClass: Class<out Activity>) {
        Assert.assertEquals(activityClass.name, currentActivity?.componentName?.className)
    }
}