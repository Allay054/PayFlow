package com.allay.payflow.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allay.payflow.core.di.PayFlowContainer
import com.allay.payflow.feature.auth.presentation.AuthViewModel
import com.allay.payflow.feature.auth.presentation.AuthViewModelFactory
import com.allay.payflow.feature.auth.presentation.LoginScreen
import com.allay.payflow.feature.auth.presentation.RegisterScreen
import com.allay.payflow.feature.home.presentation.HomeScreen

@Composable
fun PayFlowNavHost(
    container: PayFlowContainer
) {
    val navController = rememberNavController()

    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(
            loginUseCase = container.loginUseCase,
            registerUseCase = container.registerUseCase,
            authRepository = container.authRepository,
            logoutUseCase = container.logoutUseCase
        )
    )

    val authUiState by authViewModel.uiState
        .collectAsStateWithLifecycle()

    LaunchedEffect(
        authUiState.user,
        authUiState.isLoading
    ) {
        if (authUiState.isLoading) {
            return@LaunchedEffect
        }

        if (authUiState.user != null) {
            if (
                navController.currentDestination?.route !=
                PayFlowRoute.Home.route
            ) {
                navController.navigate(
                    PayFlowRoute.Home.route
                ) {
                    popUpTo(
                        PayFlowRoute.Login.route
                    ) {
                        inclusive = true
                    }
                }
            }
        } else {
            if (
                navController.currentDestination?.route ==
                PayFlowRoute.Home.route
            ) {
                navController.navigate(
                    PayFlowRoute.Login.route
                ) {
                    popUpTo(
                        PayFlowRoute.Home.route
                    ) {
                        inclusive = true
                    }
                }
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = PayFlowRoute.Login.route
    ) {

        composable(
            route = PayFlowRoute.Login.route
        ) {
            LoginScreen(
                isLoading = authUiState.isLoading,
                errorMessage = authUiState.errorMessage,
                onLoginClick = { email, password ->
                    authViewModel.login(
                        email = email,
                        password = password
                    )
                },
                onRegisterClick = {
                    authViewModel.clearError()

                    navController.navigate(
                        PayFlowRoute.Register.route
                    )
                }
            )
        }

        composable(
            route = PayFlowRoute.Register.route
        ) {
            RegisterScreen(
                isLoading = authUiState.isLoading,
                errorMessage = authUiState.errorMessage,
                onRegisterClick = { name, email, password ->
                    authViewModel.register(
                        name = name,
                        email = email,
                        password = password
                    )
                },
                onLoginClick = {
                    authViewModel.clearError()

                    navController.popBackStack()
                }
            )
        }

        composable(
            route = PayFlowRoute.Home.route
        ) {
            HomeScreen(
                onLogoutClick = {
                    authViewModel.logout()
                }
            )
        }
    }
}