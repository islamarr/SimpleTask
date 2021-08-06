package com.islam.task.data.network.response

data class Applicable(
    var code: String? = null, // AMEX
    var contractData: ContractData = ContractData(),
    var grouping: String? = null, // CREDIT_CARD
    var inputElements: List<InputElement> = listOf(),
    var label: String? = null, // American Express
    var links: LinksX = LinksX(),
    var method: String? = null, // CREDIT_CARD
    var operationType: String? = null, // CHARGE
    var recurrence: String? = null, // NONE
    var redirect: Boolean = false, // false
    var registration: String? = null, // OPTIONAL
    var selected: Boolean = false // false
)