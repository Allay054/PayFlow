package com.allay.payflow.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allay.payflow.feature.home.presentation.HomeScreen

@Composable
fun PayFlowNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PayFlowRoute.Home.route
    ) {
        composable(
            route = PayFlowRoute.Home.route
        ) {
            HomeScreen()
        }
    }
}