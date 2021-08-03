package com.islam.task.ui.paymentMethods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.task.data.repositories.PaymentRepository

class PaymentViewModelFactory(
    private val paymentRepository: PaymentRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaymentViewModel(paymentRepository) as T
    }
}