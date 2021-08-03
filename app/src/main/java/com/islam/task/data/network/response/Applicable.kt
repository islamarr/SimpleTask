package com.islam.task.data.network.response

data class Applicable(
    val code: String, // AMEX
    val contractData: ContractData,
    val grouping: String, // CREDIT_CARD
    val inputElements: List<InputElement>,
    val label: String, // American Express
    val links: LinksX,
    val method: String, // CREDIT_CARD
    val operationType: String, // CHARGE
    val recurrence: String, // NONE
    val redirect: Boolean, // false
    val registration: String, // OPTIONAL
    val selected: Boolean // false
)