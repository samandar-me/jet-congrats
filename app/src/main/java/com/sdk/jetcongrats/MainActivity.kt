package com.sdk.jetcongrats

import android.app.Activity
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
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
            BarColorsTheme(color = color)
        }
    }
}

@Composable
fun BarColorsTheme(color: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
        }
    }
}

