package com.allay.payflow.domain.usecase

import com.allay.payflow.domain.model.User
import com.allay.payflow.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): User {
        return authRepository.login(
            email = email,
            password = password
        )
    }
}