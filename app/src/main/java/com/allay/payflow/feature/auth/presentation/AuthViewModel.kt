package com.allay.payflow.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allay.payflow.domain.usecase.LoginUseCase
import com.allay.payflow.domain.usecase.RegisterUseCase
import com.allay.payflow.domain.repository.AuthRepository
import com.allay.payflow.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val authRepository: AuthRepository,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())

    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        viewModelScope.launch {
            _uiState.value = AuthUiState(
                isLoading = true
            )

            try {
                val user = authRepository.getCurrentUser()

                _uiState.value = AuthUiState(
                    user = user
                )
            } catch (e: Exception) {
                _uiState.value = AuthUiState(
                    errorMessage = e.message
                )
            }
        }
    }

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(
                isLoading = true
            )

            try {
                val user = loginUseCase(
                    email = email,
                    password = password
                )

                _uiState.value = AuthUiState(
                    user = user
                )
            } catch (e: Exception) {
                _uiState.value = AuthUiState(
                    errorMessage = e.message ?: "Login failed"
                )
            }
        }
    }

    fun register(
        name: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(
                isLoading = true
            )

            try {
                val user = registerUseCase(
                    name = name,
                    email = email,
                    password = password
                )

                _uiState.value = AuthUiState(
                    user = user
                )
            } catch (e: Exception) {
                _uiState.value = AuthUiState(
                    errorMessage = e.message ?: "Registration failed"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null
        )
    }

    fun logout() {
        viewModelScope.launch {
            _uiState.value = AuthUiState(
                isLoading = true
            )

            try {
                logoutUseCase()

                _uiState.value = AuthUiState()

            } catch (e: Exception) {
                _uiState.value = AuthUiState(
                    errorMessage = e.message ?: "Logout failed"
                )
            }
        }
    }
}