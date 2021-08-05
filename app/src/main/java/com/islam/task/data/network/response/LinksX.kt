package com.islam.task.data.network.response

data class LinksX(
    val lang: String? =null, // https://resources.integration.oscato.com/resource/lang/MOBILETEAM/en_US/AMEX.json
    val logo: String? =null, // https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png
    val operation: String? =null, // https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX/charge
    val self: String? =null, // https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX
    val validation: String? =null // https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/MOBILETEAM/en_US/AMEX/standard/validate
)