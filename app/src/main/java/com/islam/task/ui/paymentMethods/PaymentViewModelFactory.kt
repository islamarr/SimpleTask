package com.islam.task.ui.paymentMethods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.task.data.repositories.DefaultPaymentRepository

class PaymentViewModelFactory(
    private val defaultPaymentRepository: DefaultPaymentRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaymentViewModel(defaultPaymentRepository) as T
    }
}