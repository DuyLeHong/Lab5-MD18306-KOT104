package com.duyle.lab5_md18306_kot104

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


enum class ROUTE_SCREEN_NAME {
    home,
    bai3
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = ROUTE_SCREEN_NAME.home.name,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(ROUTE_SCREEN_NAME.home.name) {
            LoginApp(navController)
        }

        composable(ROUTE_SCREEN_NAME.bai3.name) {
            CategoryApp()
        }


    }
}
