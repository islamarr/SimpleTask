package com.islam.task.ui.paymentMethods

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.islam.task.R
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class PaymentFragmentTest {

    @Test
    fun activeLaunchPaymentFragment() {

        launchFragmentInContainer<PaymentFragment>(Bundle(), R.style.Theme_MyTask)

        onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("VISA"))
                )
            )

    }
}