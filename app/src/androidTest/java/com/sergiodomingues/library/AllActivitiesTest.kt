package com.sergiodomingues.library

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.FlakyTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.sergiodomingues.library.bookadd.ui.AddBookActivity
import com.sergiodomingues.library.bookmain.ui.MainActivity
import com.sergiodomingues.library.booksearch.SearchBookActivity
import com.sergiodomingues.library.testhelpers.ActivityAsserter.assertCurrentActivity
import org.hamcrest.Matchers.equalToIgnoringCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AllActivitiesTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(
        MainActivity::class.java, true, false
    )

    @Test
    fun checkListVisibility() {
        launchActivity()
        onView(withId(R.id.recViewBooksList)).check(matches(isDisplayed()))
    }

    @Test
    @FlakyTest
    fun checkBookVisibility() {
        launchActivity()
        deleteAllBooks()
        onView(withId(R.id.fabAddBook)).perform(click())
        onView(withId(R.id.fabAddBookManually)).perform(click())
        onView(withId(R.id.etTitle)).perform(typeText(testBookTitle1))
        onView(withId(R.id.itemSaveBook)).perform(click())
        onView(withText(equalToIgnoringCase(testBookTitle1))).check(matches(isDisplayed()))
    }

    @Test
    fun checkBookDeletionFromList() {
        launchActivity()
        deleteAllBooks()
        addBook(testBookTitle1)
        addBook(testBookTitle2)
        onView(withText(testBookTitle1)).check(matches(isDisplayed()))
        onView(withText(testBookTitle2)).check(matches(isDisplayed()))
        onView(withText(testBookTitle1)).perform(longClick())
        onView(withText(R.string.delete_option)).perform(click())
        onView(withText(testBookTitle1)).check(doesNotExist())
        onView(withText(testBookTitle2)).check(matches(isDisplayed()))
    }

    @Test
    fun addBook() {
        launchActivity()
        deleteAllBooks()
        onView(withId(R.id.fabAddBook)).perform(click())
        assertCurrentActivity(SearchBookActivity::class.java)
        onView(withId(R.id.fabAddBookManually)).perform(click())
        assertCurrentActivity(AddBookActivity::class.java)
        onView(withId(R.id.etTitle)).perform(
            typeText(
                testBookTitle1
            )
        )
        onView(withId(R.id.itemSaveBook)).perform(click())
        assertCurrentActivity(MainActivity::class.java)
        onView(withText(equalToIgnoringCase(testBookTitle1))).check(matches(isDisplayed()))
    }

    //Helpers

    private fun launchActivity() {
        activityTestRule.launchActivity(null)
    }

    private fun addBook(title: String) {
        onView(withId(R.id.fabAddBook)).perform(click())
        onView(withId(R.id.fabAddBookManually)).perform(click())
        onView(withId(R.id.etTitle)).perform(typeText(title))
        onView(withId(R.id.itemSaveBook)).perform(click())
    }

    private fun deleteAllBooks() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.main_delete_all_books)).perform(click())
        onView(withText(equalToIgnoringCase("Yes"))).perform(click())
    }

    companion object {
        private const val testBookTitle1 = "Testbook"
        private const val testBookTitle2 = "Testbook2"
    }
}