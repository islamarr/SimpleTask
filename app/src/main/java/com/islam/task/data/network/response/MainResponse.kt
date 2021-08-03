package com.islam.task.data.network.response

data class MainResponse(
    val identification: Identification,
    val integrationType: String, // MOBILE_NATIVE
    val interaction: Interaction,
    val links: Links,
    val networks: Networks,
    val operation: String, // LIST
    val operationType: String, // CHARGE
    val payment: Payment,
    val resultCode: String, // 00000.11.000
    val resultInfo: String, // 17 applicable and 0 registered networks are found
    val returnCode: ReturnCode,
    val status: Status,
    val style: Style,
    val timestamp: String // 2021-04-14T09:16:46.796+0000
)