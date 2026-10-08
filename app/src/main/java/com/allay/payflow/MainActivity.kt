package com.allay.payflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.allay.payflow.core.navigation.PayFlowNavHost
import com.allay.payflow.core.ui.theme.PayFlowTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PayFlowTheme {
                PayFlowNavHost()
            }
        }
    }
}