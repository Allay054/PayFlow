package com.allay.payflow.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allay.payflow.domain.repository.AuthRepository
import com.allay.payflow.domain.usecase.LoginUseCase
import com.allay.payflow.domain.usecase.RegisterUseCase
import com.allay.payflow.domain.usecase.LogoutUseCase

class AuthViewModelFactory(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val authRepository: AuthRepository,
    private val logoutUseCase: LogoutUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(
                loginUseCase = loginUseCase,
                registerUseCase = registerUseCase,
                authRepository = authRepository,
                logoutUseCase = logoutUseCase
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}