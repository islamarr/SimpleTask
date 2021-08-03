package com.islam.task.data.repositories

import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.SafeApiRequest
import com.islam.task.data.network.response.MainResponse


class PaymentRepository(private val api: MyTaskApi) : SafeApiRequest() {

    suspend fun getPaymentMethods(): MainResponse {
        return apiRequest { api.getPaymentMethods() }
    }

}