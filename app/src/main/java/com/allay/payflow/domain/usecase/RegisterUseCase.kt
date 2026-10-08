package com.allay.payflow.domain.usecase

import com.allay.payflow.domain.model.User
import com.allay.payflow.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String
    ): User {
        return authRepository.register(
            name = name,
            email = email,
            password = password
        )
    }
}