package com.islam.task.data.network.response

data class MainResponse(
    val identification: Identification,
    val integrationType: String? = null, // MOBILE_NATIVE
    val interaction: Interaction,
    val links: Links,
    val networks: Networks,
    val operation: String? = null, // LIST
    val operationType: String? = null, // CHARGE
    val payment: Payment,
    val resultCode: String? = null, // 00000.11.000
    val resultInfo: String? = null, // 17 applicable and 0 registered networks are found
    val returnCode: ReturnCode,
    val status: Status,
    val style: Style,
    val timestamp: String? = null // 2021-04-14T09:16:46.796+0000
)