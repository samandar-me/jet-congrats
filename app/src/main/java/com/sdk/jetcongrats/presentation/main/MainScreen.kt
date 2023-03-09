package com.sdk.jetcongrats.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sdk.jetcongrats.MainActivity
import com.sdk.jetcongrats.presentation.component.BottomBar
import com.sdk.jetcongrats.presentation.component.MyIconButton
import com.sdk.jetcongrats.presentation.component.NavDrawerBody
import com.sdk.jetcongrats.presentation.component.NavDrawerHeader
import com.sdk.jetcongrats.presentation.screen.BottomBarScreen
import com.sdk.jetcongrats.presentation.screen.HomeNavGraph
import com.sdk.jetcongrats.ui.theme.*
import com.sdk.jetcongrats.util.ColorObject.menuList
import com.sdk.jetcongrats.util.Constants
import com.sdk.jetcongrats.util.intent
import com.sdk.jetcongrats.util.shareThisApp
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController = rememberNavController(), color: Color, backColor: Color) {
    var title by remember {
        mutableStateOf("")
    }
    var detail by remember {
        mutableStateOf(false)
    }
    var main by remember {
        mutableStateOf(false)
    }
    val context = (LocalContext.current as MainActivity)

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = navController) {
        navController.currentBackStackEntryFlow.collect {
            when (it.destination.route) {
                BottomBarScreen.Home.route -> {
                    title = "Asosiy"
                    detail = false
                    main = true
                }
                BottomBarScreen.Favorite.route -> {
                    title = "Saqlanganlar"
                    detail = false
                    main = false
                }
                BottomBarScreen.Info.route -> {
                    title = "Sozlamalar"
                    detail = false
                    main = false
                }
                else -> {
                    title = it.arguments?.getString("title").toString()
                    detail = true
                    main = false
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = color,
                navigationIcon = {
                    if (detail) {
                        MyIconButton(icon = Icons.Filled.KeyboardArrowLeft) {
                            navController.popBackStack()
                        }
                    } else if (main) {
                        MyIconButton(icon = Icons.Filled.Menu) {
                            coroutineScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    } else {
                        MyIconButton(icon = Icons.Filled.KeyboardArrowLeft) {
                            navController.navigate(BottomBarScreen.Home.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    }
                },
                title = {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontFamily = BoldFont,
                        color = TextColor,
                        maxLines = 1
                    )
                }
            )
        },
        bottomBar = { BottomBar(navController = navController, color) },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            NavDrawerHeader()
            NavDrawerBody(
                items = menuList(),
                onItemClick = {
                    when(it.id) {
                        0 -> context.intent("${Constants.URL}${context.packageName}")
                        1 -> context.intent(Constants.USER_NAME)
                        2 -> context.shareThisApp()
                        3 -> context.intent(Constants.PROVERBS)
                        4 -> context.intent(Constants.AGATHA)
                        5 -> context.intent(Constants.ASTRA)
                        6 -> context.intent(Constants.DRAW)
                        7 -> context.intent(Constants.MY_APPS)
                    }
                }
            )
        },
        drawerBackgroundColor = color,
        backgroundColor = backColor
    ) {
        HomeNavGraph(navController = navController, color = color, backColor = backColor)
        BackHandler {
            if (scaffoldState.drawerState.isOpen) {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            } else {
                if (!main) {
                    navController.popBackStack()
                } else {
                    context.finish()
                }
            }
        }
    }
}