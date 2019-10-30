package io.bloco.biblioteca

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.FlakyTest
import androidx.test.rule.ActivityTestRule
import io.bloco.biblioteca.testhelpers.AssertCurrentActivity.assertCurrentActivity
import io.bloco.biblioteca.testhelpers.BookFactory
import org.hamcrest.Matchers.equalToIgnoringCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)
    private val repository = BookRepository

    @Test
    fun checkListVisibility() {
        launchActivity()
        onView(withId(R.id.lisfOfBooks)).check(matches(isDisplayed()))
    }

    @Test
    @FlakyTest
    fun checkBookVisibility() {
        clearRepositoryBooks()
        val book = BookFactory.makeBook(TEST_BOOK)
        repository.addBook(book){}
        waitForAddBookCallBack(1)
        launchActivity()
        onView(withText(TEST_BOOK)).check(matches(isDisplayed()))
    }

    @Test
    fun checkBookDeletionFromList() {
        clearRepositoryBooks()
        val book1 = BookFactory.makeBook(TEST_BOOK)
        val book2 = BookFactory.makeBook()
        repository.addBook(book1){}
        repository.addBook(book2){}
        waitForAddBookCallBack(2)
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
        clearRepositoryBooks()
        launchActivity()
        onView(withId(R.id.addBookBtn)).perform(click())
        assertCurrentActivity(AddBookActivity::class.java)
        onView(withId(R.id.etTitle)).perform(typeText(TEST_BOOK))
        onView(withId(R.id.saveBook)).perform(click())
        assertCurrentActivity(MainActivity::class.java)
        onView(withText(equalToIgnoringCase(TEST_BOOK))).check(matches(isDisplayed()))
    }

    //Helpers

    private fun launchActivity() {
        activityTestRule.launchActivity(null)
    }

    private fun clearRepositoryBooks() {
        repository.clearRepository()
    }

    private fun waitForAddBookCallBack(expectedBooks: Int) {
        var counter = 1
        while (repository.size() != expectedBooks && counter < 10) {
            Thread.sleep(100)
            counter++
        }
    }

    companion object {
        private const val TEST_BOOK = "testbook"
    }
}