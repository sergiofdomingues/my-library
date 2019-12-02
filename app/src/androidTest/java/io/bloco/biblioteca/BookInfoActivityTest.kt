package io.bloco.biblioteca

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.bloco.biblioteca.activities.BookInfoActivity
import io.bloco.biblioteca.helpers.DateHelpers.dateToString
import io.bloco.biblioteca.helpers.IntentManager
import io.bloco.biblioteca.testhelpers.AppHelper.app
import io.bloco.biblioteca.testhelpers.BookFactory
import io.bloco.biblioteca.testhelpers.InstrumentationContext
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookInfoActivityTest {

    private var intentManager: IntentManager = app.component.intentManager()

    @get:Rule
    var activityTestRule =
        ActivityTestRule<BookInfoActivity>(
            BookInfoActivity::class.java, true, false
        )

    @Test
    fun checkIfBookDetailsAreDisplayed() {
        val book = BookFactory.makeCompleteBook()
        val intent = intentManager.getIntentBookInfoActivity(
            InstrumentationContext.useContext(),
            book
        )
        activityTestRule.launchActivity(intent)
        // Check if book details are displayed
        onView(withText(book.title)).check(matches(isDisplayed()))
        onView(withText(book.author)).check(matches(isDisplayed()))
        onView(withText(book.isbn)).check(matches(isDisplayed()))
        onView(withText(dateToString(book.publishDate))).check(matches(isDisplayed()))
    }
}