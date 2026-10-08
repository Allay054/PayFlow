package com.allay.payflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.allay.payflow.core.di.PayFlowContainer
import com.allay.payflow.core.navigation.PayFlowNavHost
import com.allay.payflow.core.ui.theme.PayFlowTheme

class MainActivity : ComponentActivity() {

    private val payFlowContainer by lazy {
        PayFlowContainer(
            context = applicationContext
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PayFlowTheme {
                PayFlowNavHost(
                    container = payFlowContainer
                )
            }
        }
    }
}