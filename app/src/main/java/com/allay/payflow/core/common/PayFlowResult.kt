package com.allay.payflow.core.common

sealed interface PayFlowResult<out T> {

    data class Success<T>(
        val data: T
    ) : PayFlowResult<T>

    data class Error(
        val message: String,
        val cause: Throwable? = null
    ) : PayFlowResult<Nothing>
}