package com.islam.task.repositories

import com.islam.task.data.Resource
import com.islam.task.data.network.response.*
import com.islam.task.data.repositories.PaymentRepository

class FakePaymentMethodRepo : PaymentRepository {

    private var shouldReturnNetworkError = false
    private val applicableItems = mutableListOf<Applicable>()

    override suspend fun getPaymentMethods(): Resource<MainResponse> {
        applicableItems.add(
            Applicable(
                "AMEX", ContractData(), "CREDIT_CARD", listOf(), "American Express",
                LinksX(), "CREDIT_CARD", "CHARGE", "NONE", false,
                "OPTIONAL", false
            )
        )
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