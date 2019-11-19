package io.bloco.biblioteca.testhelpers

import android.app.Activity
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import io.bloco.biblioteca.BookRepository
import org.junit.Assert

object TestHelpers {

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

    fun waitForAddBookCallBack(expectedBooks: Int, repository: BookRepository) {
        var threshold = 1
        while (repository.getNumBooks() != expectedBooks && threshold < 10) {
            Thread.sleep(100)
            threshold++
        }
    }

    fun useContext(): Context {
        return InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    }
}