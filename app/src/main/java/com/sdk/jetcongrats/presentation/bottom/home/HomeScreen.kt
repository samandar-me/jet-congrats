package com.sdk.jetcongrats.presentation.bottom.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.jetcongrats.presentation.component.BoxItem
import com.sdk.jetcongrats.util.Graph

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val list by remember {
        viewModel.list
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 55.dp)
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(all = 7.dp)
        ) {
            items(items = list) { item ->
                BoxItem(feature = item) {
                    navController.navigate(route = "${Graph.DETAILS}/${item.id}/${item.title}")
                }
            }
        }
    }
}