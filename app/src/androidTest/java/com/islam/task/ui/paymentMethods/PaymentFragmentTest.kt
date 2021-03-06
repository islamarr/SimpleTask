package com.islam.task.ui.paymentMethods

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.islam.task.R
import com.islam.task.launchFragmentInHiltContainer
import com.islam.task.repositories.FakePaymentMethodRepoForUI
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class PaymentFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val ITEM_BELOW_THE_FOLD = 20

    @Before
    fun setup() {

        launchFragmentInHiltContainer<PaymentFragment>(Bundle(), R.style.Theme_MyTask)

    }

    @Test
    fun check_isListVisible_onFragmentLaunch() {

        onView(withId(R.id.list)).check(matches(isDisplayed()))

    }

    @Test
    fun check_isItemTextDisplayed() {

        onView(withId(R.id.list)).check(matches(atPosition(0, hasDescendant(withText("VISA_1")))))

    }

    @Test
    fun scrollToItemBelowFold_checkItsText() {

        onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    ITEM_BELOW_THE_FOLD,
                    ViewActions.click()
                )
            )

        val itemElementText = "VISA_$ITEM_BELOW_THE_FOLD"
        onView(withText(itemElementText)).check(matches(isDisplayed()))

    }

    @Test
    fun scrollToSpecificItem_doExist() {

        onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("VISA_$ITEM_BELOW_THE_FOLD"))
                )
            )

    }

    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {

        onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("not in the list"))
                )
            )
    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }
        }
    }

}