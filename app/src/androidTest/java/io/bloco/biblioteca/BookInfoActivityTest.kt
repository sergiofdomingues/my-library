package io.bloco.biblioteca

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import io.bloco.biblioteca.testhelpers.BookFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookInfoActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<BookInfoActivity>(BookInfoActivity::class.java, true, false)

    @Test
    fun checkIfBookDetailsAreDisplayed() {
        val book = BookFactory.makeBook()
        val intent = BookInfoActivity.getIntent(InstrumentationRegistry.getInstrumentation().targetContext, book)
        activityTestRule.launchActivity(intent)
        // check if book details are displayed
        onView(withText(book.title)).check(matches(isDisplayed()))
        onView(withText(book.author)).check(matches(isDisplayed()))
        onView(withText(book.isbn)).check(matches(isDisplayed()))
        onView(withText(book.publishDate)).check(matches(isDisplayed()))
    }
}