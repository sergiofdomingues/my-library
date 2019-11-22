package io.bloco.biblioteca

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.FlakyTest
import androidx.test.rule.ActivityTestRule
import io.bloco.biblioteca.activities.AddBookActivity
import io.bloco.biblioteca.activities.MainActivity
import io.bloco.biblioteca.activities.SearchBookActivity
import io.bloco.biblioteca.testhelpers.ActivityAsserter.assertCurrentActivity
import io.bloco.biblioteca.testhelpers.BookFactory
import io.bloco.biblioteca.testhelpers.InstrumentationContext
import io.bloco.biblioteca.testhelpers.Waiter.waitForAddBookCallBack
import org.hamcrest.Matchers.equalToIgnoringCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java, true, false
    )
    private val repository = (InstrumentationContext.useContext() as App).getBookRepository()

    @Test
    fun checkListVisibility() {
        launchActivity()
        onView(withId(R.id.recViewBooksList)).check(matches(isDisplayed()))
    }

    @Test
    @FlakyTest
    fun checkBookVisibility() {
        repository.deleteAllBooksInDb()
        val book = BookFactory.makeCompleteBook(TEST_BOOK)
        repository.addBook(book)
        waitForAddBookCallBack(1, repository)
        launchActivity()
        onView(withText(TEST_BOOK)).check(matches(isDisplayed()))
    }

    @Test
    fun checkBookDeletionFromList() {
        repository.deleteAllBooksInDb()
        val book1 = BookFactory.makeCompleteBook(TEST_BOOK)
        val book2 = BookFactory.makeCompleteBook()
        repository.addBook(book1)
        repository.addBook(book2)
        waitForAddBookCallBack(2, repository)
        launchActivity()
        onView(withText(book1.title)).check(matches(isDisplayed()))
        onView(withText(book2.title)).check(matches(isDisplayed()))
        onView(withText(book1.title)).perform(longClick())
        onView(withText(R.string.delete_option)).perform(click())
        onView(withText(book1.title)).check(doesNotExist())
        onView(withText(book2.title)).check(matches(isDisplayed()))
    }

    @Test
    fun addBook() {
        repository.deleteAllBooksInDb()
        launchActivity()
        onView(withId(R.id.fabAddBook)).perform(click())
        assertCurrentActivity(SearchBookActivity::class.java)
        onView(withId(R.id.itemAddBook)).perform(click())
        assertCurrentActivity(AddBookActivity::class.java)
        onView(withId(R.id.etTitle)).perform(typeText(TEST_BOOK))
        onView(withId(R.id.etDate)).perform(click())
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.itemSaveBook)).perform(click())
        assertCurrentActivity(MainActivity::class.java)
        onView(withText(equalToIgnoringCase(TEST_BOOK))).check(matches(isDisplayed()))
    }

    //Helpers

    private fun launchActivity() {
        activityTestRule.launchActivity(null)
    }

    companion object {
        private const val TEST_BOOK = "testbook"
    }

}