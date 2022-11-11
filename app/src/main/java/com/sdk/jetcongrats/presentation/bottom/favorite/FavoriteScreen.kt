package com.sdk.jetcongrats.presentation.bottom.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.sdk.jetcongrats.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sdk.jetcongrats.presentation.component.Loading
import com.sdk.jetcongrats.presentation.component.MyIconButton
import com.sdk.jetcongrats.ui.theme.*
import com.sdk.jetcongrats.util.Graph
import com.sdk.jetcongrats.presentation.component.Error
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavHostController, color: Color, backColor: Color) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel: FavoriteViewModel = hiltViewModel()
    val state by remember {
        viewModel.state
    }
    if (state.loading) {
        Loading(color = color)
    }
    if (state.error.isNotBlank()) {
        Error(state.error)
    }
    state.success?.let { list ->
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 55.dp),
            scaffoldState = scaffoldState,
            backgroundColor = backColor
        ) { _ ->
            if (list.isEmpty()) {
                Empty()
            }
            LazyColumn(
                contentPadding = PaddingValues(2.dp)
            ) {
                items(items = list) {
                    CardItem(
                        from = it.from,
                        title = it.text,
                        color = color,
                        onClick = { bool ->
                            if (bool) {

                            } else {

                            }
                        },
                        onItemClick = {
                            navController.navigate(route = "${Graph.DETAILS}/${it.itemId}/${it.from}")
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    from: String,
    title: String,
    color: Color,
    onClick: (Boolean) -> Unit,
    onItemClick: () -> Unit
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
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp, horizontal = 4.dp),
        backgroundColor = color,
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        onClick = onItemClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Text(
                text = "$from dan",
                fontSize = 12.sp,
                color = TextColor,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(top = 3.dp, start = 10.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = actualQuestion.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                color = TextColor,
                fontSize = 18.sp,
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
                MyIconButton(icon = painterResource(id = R.drawable.ic_baseline_content_copy)) {
                    onClick(true)
                }
                Spacer(modifier = Modifier.width(3.dp))
                MyIconButton(icon = Icons.Outlined.Delete) {
                    onClick(false)
                }
            }
        }
    }
}

@Composable
fun Empty() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }
}