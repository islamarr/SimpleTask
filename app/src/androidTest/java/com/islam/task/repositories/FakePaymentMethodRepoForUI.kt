package com.islam.task.repositories

import com.islam.task.data.Resource
import com.islam.task.data.network.response.*
import com.islam.task.data.repositories.PaymentRepository

class FakePaymentMethodRepoForUI : PaymentRepository {

    private var shouldReturnNetworkError = false
    private val applicableItems = mutableListOf<Applicable>()

    override suspend fun getPaymentMethods(): Resource<MainResponse> {
        applicableItems.add(
            Applicable(
                "VISA", ContractData(), "CREDIT_CARD", listOf(), "American Express",
                LinksX(
                    "https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png",
                    "https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX",
                    "https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX",
                    "https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX"
                ), "CREDIT_CARD", "CHARGE", "NONE", false,
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