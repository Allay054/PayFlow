package com.allay.payflow.feature.auth.presentation

import com.allay.payflow.domain.model.User

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)