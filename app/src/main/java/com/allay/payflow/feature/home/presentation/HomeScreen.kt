package com.allay.payflow.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "PayFlow",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Digital Wallet & Payment Sandbox",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Phase 1 Foundation",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}