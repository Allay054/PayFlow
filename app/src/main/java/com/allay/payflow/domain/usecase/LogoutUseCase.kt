package com.allay.payflow.domain.usecase

import com.allay.payflow.domain.repository.AuthRepository

class LogoutUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() {
        authRepository.logout()
    }
}