package com.sdk.jetcongrats.presentation.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuItem(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)