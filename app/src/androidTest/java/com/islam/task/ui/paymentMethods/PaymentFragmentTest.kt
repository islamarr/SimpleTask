package com.islam.task.ui.paymentMethods

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.islam.task.R
import com.islam.task.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class PaymentFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        launchFragmentInHiltContainer<PaymentFragment>(Bundle(), R.style.Theme_MyTask)

        Thread.sleep(5000)
    }

    @Test
    fun scrollToSpecificItem_doExist() {

        onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("VISA"))
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
}