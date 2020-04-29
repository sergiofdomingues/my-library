package com.sergiodomingues.library

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.sergiodomingues.library.bookdetails.BookDetailsActivity
import com.sergiodomingues.library.helpers.DateHelpers.dateToString
import com.sergiodomingues.library.testhelpers.BookFactory
import com.sergiodomingues.library.testhelpers.InstrumentationContext
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookDetailsActivityTest {

    @get:Rule
    var activityTestRule =
        ActivityTestRule(
            BookDetailsActivity::class.java, true, false
        )

    @Test
    fun checkIfBookDetailsAreDisplayed() {
        val book = BookFactory.makeCompleteBook()
        val intent = BookDetailsActivity.getIntent(
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