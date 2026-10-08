package com.allay.payflow.core.di

import android.content.Context
import com.allay.payflow.data.local.AuthLocalDataSource
import com.allay.payflow.data.repository.LocalAuthRepository
import com.allay.payflow.domain.repository.AuthRepository
import com.allay.payflow.domain.usecase.LoginUseCase
import com.allay.payflow.domain.usecase.LogoutUseCase
import com.allay.payflow.domain.usecase.RegisterUseCase

class PayFlowContainer(
    context: Context
) {

    private val authLocalDataSource =
        AuthLocalDataSource(context)

    val authRepository: AuthRepository =
        LocalAuthRepository(
            localDataSource = authLocalDataSource
        )

    val loginUseCase = LoginUseCase(
        authRepository = authRepository
    )

    val registerUseCase = RegisterUseCase(
        authRepository = authRepository
    )

    val logoutUseCase = LogoutUseCase(
        authRepository = authRepository
    )
}