package com.islam.task.data.repositories

import com.islam.task.data.Resource
import com.islam.task.data.network.response.MainResponse

interface PaymentRepository {

    suspend fun getPaymentMethods(): Resource<MainResponse>

}