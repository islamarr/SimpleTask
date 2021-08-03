package com.islam.task.ui.paymentMethods

import androidx.lifecycle.ViewModel
import com.islam.task.data.network.response.MainResponse
import com.islam.task.data.repositories.PaymentRepository

class PaymentViewModel(private val paymentRepository: PaymentRepository) :
    ViewModel() {

    suspend fun getPaymentMethods(): MainResponse {
        return paymentRepository.getPaymentMethods()
    }

}