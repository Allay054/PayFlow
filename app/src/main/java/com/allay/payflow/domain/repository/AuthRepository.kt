package com.allay.payflow.domain.repository

import com.allay.payflow.domain.model.User

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): User

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): User

    suspend fun logout()

    suspend fun getCurrentUser(): User?
}