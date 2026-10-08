package com.allay.payflow.core.navigation

sealed class PayFlowRoute(
    val route: String
) {

    data object Home : PayFlowRoute("home")

    data object Wallet : PayFlowRoute("wallet")

    data object Transactions : PayFlowRoute("transactions")

    data object SendMoney : PayFlowRoute("send_money")

    data object RequestMoney : PayFlowRoute("request_money")

    data object Profile : PayFlowRoute("profile")
}