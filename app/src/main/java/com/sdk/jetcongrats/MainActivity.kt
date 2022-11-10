package com.sdk.jetcongrats

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.sdk.jetcongrats.presentation.bottom.settings.SettingsViewModel
import com.sdk.jetcongrats.presentation.screen.RootNavigationGraph
import com.sdk.jetcongrats.ui.theme.Grey10
import com.sdk.jetcongrats.ui.theme.JetCongratsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            val color by animateColorAsState(targetValue = viewModel.color.value)
            val backColor by animateColorAsState(targetValue = if (viewModel.backColor.value) Grey10 else Color.White)
            RootNavigationGraph(
                navController = rememberNavController(),
                color = color,
                backColor = backColor
            )
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                window.statusBarColor = android.graphics.Color.rgb(color.red, color.green, color.blue)
            }
        }
    }
}

