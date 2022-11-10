package com.sdk.jetcongrats.data

import androidx.compose.ui.graphics.Color
import com.sdk.jetcongrats.ui.theme.*

data class BoxFeature(
    val id: String,
    val title: String,
    val boxColor: BoxColor = BoxColor()
)
data class BoxColor(
    val lightColor: Color = BlueViolet1,
    val mediumColor: Color = BlueViolet2,
    val darkColor: Color = BlueViolet3
)