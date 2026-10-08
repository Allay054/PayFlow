package com.allay.payflow.data.repository

import com.allay.payflow.data.local.AuthLocalDataSource
import com.allay.payflow.domain.model.User
import com.allay.payflow.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class LocalAuthRepository(
    private val localDataSource: AuthLocalDataSource
) : AuthRepository {

    private val users = mutableListOf(
        User(
            id = "demo-user-1",
            name = "Demo User",
            email = "demo@payflow.com"
        )
    )

    private var currentUser: User? = null

    override suspend fun login(
        email: String,
        password: String
    ): User {
        delay(800)

        if (email == "demo@payflow.com" && password == "123456") {
            val user = users.first()

            currentUser = user

            localDataSource.saveUser(
                id = user.id,
                name = user.name,
                email = user.email
            )

            return user
        }

        throw IllegalArgumentException(
            "Invalid email or password"
        )
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): User {
        delay(800)

        if (name.isBlank()) {
            throw IllegalArgumentException(
                "Name is required"
            )
        }

        if (email.isBlank()) {
            throw IllegalArgumentException(
                "Email is required"
            )
        }

        if (password.length < 6) {
            throw IllegalArgumentException(
                "Password must contain at least 6 characters"
            )
        }

        if (users.any {
                it.email.equals(
                    email,
                    ignoreCase = true
                )
            }
        ) {
            throw IllegalArgumentException(
                "An account with this email already exists"
            )
        }

        val user = User(
            id = "user-${users.size + 1}",
            name = name,
            email = email
        )

        users.add(user)

        currentUser = user

        localDataSource.saveUser(
            id = user.id,
            name = user.name,
            email = user.email
        )

        return user
    }

    override suspend fun logout() {
        delay(300)

        currentUser = null

        localDataSource.clearUser()
    }

    override suspend fun getCurrentUser(): User? {
        currentUser?.let {
            return it
        }

        val savedUser = localDataSource.getUser()
            ?: return null

        val user = User(
            id = savedUser.id,
            name = savedUser.name,
            email = savedUser.email
        )

        currentUser = user

        return user
    }
}