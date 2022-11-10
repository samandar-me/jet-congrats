package com.sdk.jetcongrats.presentation.splash

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.jetcongrats.R
import com.sdk.jetcongrats.ui.theme.TextColor
import com.sdk.jetcongrats.util.Graph
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
   // val color by animateColorAsState(targetValue = viewModel.color.value)
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1200
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1200)
        navController.popBackStack()
        navController.navigate(Graph.HOME)
    }
    Splash(alpha = alphaAnim.value, color = Color.Black)
}

@Composable
fun Splash(alpha: Float, color: Color) {
    Box(
        modifier = Modifier
            .background(color)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(color),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.poety),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(200.dp)
                    .alpha(alpha)
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.tabrik),
                fontFamily = FontFamily.Cursive,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = TextColor
            )
        }
    }
}