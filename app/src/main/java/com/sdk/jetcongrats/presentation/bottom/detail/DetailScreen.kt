package com.sdk.jetcongrats.presentation.bottom.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sdk.jetcongrats.R
import com.sdk.jetcongrats.domain.model.FavoriteData
import com.sdk.jetcongrats.presentation.bottom.settings.SettingsViewModel
import com.sdk.jetcongrats.presentation.component.Error
import com.sdk.jetcongrats.presentation.component.Loading
import com.sdk.jetcongrats.presentation.component.MyIconButton
import com.sdk.jetcongrats.ui.theme.BoldFont
import com.sdk.jetcongrats.ui.theme.Grey10
import com.sdk.jetcongrats.ui.theme.Grey90
import com.sdk.jetcongrats.ui.theme.TextColor
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(title: String, id: String) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val detailViewModel: DetailViewModel = hiltViewModel()
    val viewModel: SettingsViewModel = hiltViewModel()
    val color by animateColorAsState(targetValue = viewModel.color.value)
    val backColor by animateColorAsState(targetValue = if (viewModel.backColor.value) Grey10 else Color.White)
    LaunchedEffect(key1 = Unit) {
        detailViewModel.whichItem(id)
    }
    val state by remember {
        detailViewModel.state
    }
    if (state.loading) {
        Loading(color)
    }
    if (state.error.isNotBlank()) {
        Error(state.error)
    }
    state.success?.let { list ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            backgroundColor = backColor
        ) {
            LazyColumn(
                contentPadding = PaddingValues(2.dp)
            ) {
                itemsIndexed(list) { index, item ->
                    ItemCard(
                        index = index,
                        title = item.title,
                        color = color,
                        onCopyClick = {
                            coroutineScope.launch {
                                detailViewModel.copyText(item.title)
                                scaffoldState.snackbarHostState.showSnackbar(
                                    "Nusxalandi"
                                )
                            }
                        },
                        onSaveClick = {
                            coroutineScope.launch {
                                detailViewModel.saveToFavorite(
                                    FavoriteData(
                                        from = title,
                                        text = item.title,
                                        itemId = id
                                    )
                                )
                                scaffoldState.snackbarHostState.showSnackbar(
                                    "Saqlandi"
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun ItemCard(
    index: Int,
    title: String,
    color: Color,
    onSaveClick: () -> Unit,
    onCopyClick: () -> Unit
) {
    val contains by remember {
        mutableStateOf(title.contains("#"))
    }
    val actualQuestion by remember {
        mutableStateOf(if (contains) title.subSequence(0, title.indexOf("#")) else title)
    }
    val answer by remember {
        mutableStateOf(
            if (contains) title.subSequence(
                title.indexOf("#") + 1,
                title.length
            ) else ""
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp, horizontal = 4.dp),
        backgroundColor = color,
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .background(color)
        ) {
            Text(
                text = "${index.plus(1)}",
                fontSize = 14.sp,
                color = TextColor,
                fontFamily = BoldFont,
                modifier = Modifier.padding(3.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = actualQuestion.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                color = TextColor,
                fontSize = 17.sp,
                fontFamily = BoldFont
            )
            Spacer(modifier = Modifier.height(7.dp))
            if (contains) {
                Text(
                    text = answer.toString(),
                    fontSize = 18.sp,
                    color = Grey90,
                    modifier = Modifier
                        .rotate(180f)
                        .alpha(0.5f)
                        .padding(end = 10.dp, top = 10.dp),
                    fontStyle = FontStyle.Italic
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp), horizontalArrangement = Arrangement.End
            ) {
                MyIconButton(icon = painterResource(id = R.drawable.ic_baseline_save_alt)) {
                    onSaveClick()
                }
                Spacer(modifier = Modifier.width(3.dp))
                MyIconButton(icon = painterResource(id = R.drawable.ic_baseline_content_copy)) {
                    onCopyClick()
                }
            }
        }
    }
}