package com.sdk.jetcongrats.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        color = color,
        shape = CircleShape,
        modifier = modifier
            .padding(1.dp)
            .size(38.dp)
            .clickable {
                onClick()
            },
        content = { }
    )
}