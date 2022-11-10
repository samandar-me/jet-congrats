package com.sdk.jetcongrats.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sdk.jetcongrats.presentation.main.MainScreen
import com.sdk.jetcongrats.util.Graph

@Composable
fun RootNavigationGraph(navController: NavHostController, color: Color, backColor: Color) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        splashNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            MainScreen(color = color, backColor = backColor)
        }
    }
}