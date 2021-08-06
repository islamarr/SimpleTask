package com.islam.task.repositories

import com.islam.task.data.Resource
import com.islam.task.data.network.response.*
import com.islam.task.data.repositories.PaymentRepository

class FakePaymentMethodRepo(val applicableItems : MutableList<Applicable>) : PaymentRepository {

    private var shouldReturnNetworkError = false

    override suspend fun getPaymentMethods(): Resource<MainResponse> {

        return if (shouldReturnNetworkError) {
            Resource.Error("Error")
        } else {
            Resource.Success(
                MainResponse(
                    Identification(), null, Interaction(), Links(),
                    Networks(applicableItems), null, null, Payment(), null,
                    null, ReturnCode(), Status(), Style(), null
                )
            )
        }
    }

}