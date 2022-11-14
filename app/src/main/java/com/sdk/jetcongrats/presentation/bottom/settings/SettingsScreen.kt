package com.sdk.jetcongrats.presentation.bottom.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sdk.jetcongrats.R
import com.sdk.jetcongrats.presentation.component.RoundButton
import com.sdk.jetcongrats.presentation.component.SettingItem
import com.sdk.jetcongrats.ui.theme.BoldFont
import com.sdk.jetcongrats.util.ColorObject

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    val isChecked by remember {
        viewModel.backColor
    }
    val stokeColor by animateColorAsState(targetValue = if (isChecked) Color.White else Color.Black)
    val backColor by animateColorAsState(targetValue = viewModel.color.value)
    LazyColumn(
        contentPadding = PaddingValues(4.dp)
    ) {
        item {
            SettingItem(color = backColor, height = 150.dp) {
                Text(
                    text = stringResource(id = R.string.app_color),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = BoldFont,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(25.dp))
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
                                    color = if (viewModel.color.value == item) stokeColor else Color.Transparent,
                                    shape = CircleShape
                                )
                        ) {
                            viewModel.saveColor(index)
                        }
                    }
                }
            }
        }
        item {
            SettingItem(height = 250.dp, color = backColor) {
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Text(
                    text = stringResource(id = R.string.fon_color),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = BoldFont,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (!isChecked) Color.White else backColor)
                            .clickable {
                                viewModel.saveBackColor(false)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.day),
                            contentDescription = "Day",
                            modifier = Modifier.size(80.dp),
                            tint = Color.Black
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isChecked) Color.White else backColor)
                            .clickable {
                                viewModel.saveBackColor(true)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.night),
                            contentDescription = "Night",
                            modifier = Modifier.size(80.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}
