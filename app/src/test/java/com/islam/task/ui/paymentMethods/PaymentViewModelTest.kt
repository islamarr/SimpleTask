package com.islam.task.ui.paymentMethods

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.islam.task.MainCoroutineRule
import com.islam.task.data.Resource
import com.islam.task.data.network.response.Applicable
import com.islam.task.data.network.response.ContractData
import com.islam.task.data.network.response.LinksX
import com.islam.task.data.succeeded
import com.islam.task.getOrAwaitValue
import com.islam.task.repositories.FakePaymentMethodRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
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

    @Test
    fun `load PaymentMethods notNull`() {

        val applicableItems = mutableListOf<Applicable>()
        applicableItems.add(
            Applicable(
                "AMEX", ContractData(), "CREDIT_CARD", listOf(), "American Express",
                LinksX(), "CREDIT_CARD", "CHARGE", "NONE", false,
                "OPTIONAL", false
            )
        )
        viewModel = PaymentViewModel(FakePaymentMethodRepo(applicableItems))

        viewModel.getPaymentMethods()

        val result = viewModel.methods.getOrAwaitValue()
        val resultList = (result as Resource.Success).data.networks.applicable

        assertThat(result, not(nullValue()))
        assertThat(resultList[0].code, `is`(applicableItems[0].code))
        assertThat(resultList[0].grouping, `is`(applicableItems[0].grouping))
        assertThat(resultList[0].method, `is`(applicableItems[0].method))
        assertThat(resultList.size, `is`(1))

    }

    @Test
    fun `load empty PaymentMethodList return Empty List`() {

        viewModel = PaymentViewModel(FakePaymentMethodRepo(mutableListOf()))

        viewModel.getPaymentMethods()

        val result = viewModel.methods.getOrAwaitValue()
        val resultList = (result as Resource.Success).data.networks.applicable

        assertThat(resultList, `is`(emptyList()))
    }
}