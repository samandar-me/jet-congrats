package com.sdk.jetcongrats.presentation.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.painter.Painter

data class MenuItem(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)