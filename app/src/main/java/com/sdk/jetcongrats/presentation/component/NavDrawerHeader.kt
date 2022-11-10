package com.sdk.jetcongrats.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.jetcongrats.R
import com.sdk.jetcongrats.ui.theme.*

@Composable
fun NavDrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.poety),
                contentDescription = "Poetry",
                alignment = Alignment.Center,
                modifier = Modifier.size(130.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                color = TextColor,
                fontFamily = BoldFont
            )
        }
    }
}