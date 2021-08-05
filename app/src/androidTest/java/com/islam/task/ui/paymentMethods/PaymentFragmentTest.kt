package com.islam.task.ui.paymentMethods

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.islam.task.R
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class PaymentFragmentTest {

    @Test
    fun activeTaskDetails_DisplayedInUi() {

        val scenario = launchFragmentInContainer<PaymentFragment>(Bundle(), R.style.Theme_MyTask)


        Thread.sleep(7000)


    }
}