package com.sdk.jetcongrats.presentation.screen

import com.sdk.jetcongrats.R

sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = "HOME",
        icon = R.drawable.home
    )

    object Favorite : BottomBarScreen(
        route = "FAVORITE",
        icon = R.drawable.favorite
    )

    object Info : BottomBarScreen(
        route = "INFO",
        icon = R.drawable.info
    )
}
