package com.islam.task.ui.paymentMethods

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.islam.task.MainCoroutineRule
import com.islam.task.getOrAwaitValue
import com.islam.task.repositories.FakePaymentMethodRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before

import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PaymentViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PaymentViewModel

    @Before
    fun setUpPaymentViewModel() {
        viewModel = PaymentViewModel(FakePaymentMethodRepo())
    }

    @Test
    fun `load PaymentMethods`() {

        viewModel.getPaymentMethods()

        val value = viewModel.methods.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled(), not(nullValue()))
    }
}