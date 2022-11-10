package com.sdk.jetcongrats.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.sdk.jetcongrats.ui.theme.TextColor

@Composable
fun MyIconButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = "icon",
            tint = TextColor
        )
    }
}
@Composable
fun MyIconButton(icon: Painter, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = "icon",
            tint = TextColor
        )
    }
}