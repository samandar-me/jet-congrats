package com.sdk.jetcongrats.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sdk.jetcongrats.presentation.bottom.detail.DetailScreen
import com.sdk.jetcongrats.presentation.bottom.favorite.FavoriteScreen
import com.sdk.jetcongrats.presentation.bottom.home.HomeScreen
import com.sdk.jetcongrats.presentation.bottom.settings.SettingsScreen
import com.sdk.jetcongrats.presentation.splash.SplashScreen
import com.sdk.jetcongrats.util.Graph

fun NavGraphBuilder.splashNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SPLASH,
        startDestination = "SPLASH"
    ) {
        composable(
            route = "SPLASH"
        ) {
            SplashScreen(navController)
        }
    }
}

@Composable
fun HomeNavGraph(navController: NavHostController, color: Color, backColor: Color) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Favorite.route) {
            FavoriteScreen(navController, color = color, backColor = backColor)
        }
        composable(route = BottomBarScreen.Info.route) {
            SettingsScreen()
        }
        detailsNavGraph()
    }
}

fun NavGraphBuilder.detailsNavGraph() {
    navigation(
        route = "${Graph.DETAILS}/{id}/{title}",
        startDestination = "DETAIL"
    ) {
        composable(
            route = "DETAIL",
            arguments = listOf(
                navArgument(
                    name = "title"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "id"
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            val title = it.arguments?.getString("title") ?: ""
            val id = it.arguments?.getString("id") ?: ""
            DetailScreen(title = title, id = id)
        }
    }
}
