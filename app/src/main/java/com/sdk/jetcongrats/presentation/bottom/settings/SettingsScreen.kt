package com.sdk.jetcongrats.presentation.bottom.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.angads25.toggle.widget.DayNightSwitch
import com.sdk.jetcongrats.R
import com.sdk.jetcongrats.presentation.component.RoundButton
import com.sdk.jetcongrats.ui.theme.BoldFont
import com.sdk.jetcongrats.util.ColorObject

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    val isChecked by remember {
        viewModel.backColor
    }
    val textColor by animateColorAsState(targetValue = if (isChecked) Color.White else Color.Black)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.app_color),
            textAlign = TextAlign.Center,
            color = textColor,
            fontFamily = BoldFont,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            contentPadding = PaddingValues(vertical = 10.dp, horizontal = 8.dp)
        ) {
            itemsIndexed(ColorObject.colorList) { index, item ->
                RoundButton(
                    color = item,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = if (viewModel.color.value == item) textColor else Color.Transparent,
                            shape = CircleShape
                        )
                ) {
                    viewModel.saveColor(index)
                }
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = stringResource(id = R.string.fon_color),
            textAlign = TextAlign.Center,
            color = textColor,
            fontFamily = BoldFont,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(14.dp))
        AndroidView(
            factory = {
                DayNightSwitch(it).apply {
                    isOn = isChecked
                    setOnToggledListener { _, isOn ->
                        viewModel.saveBackColor(isOn)
                    }
                }
            }
        )
    }
}