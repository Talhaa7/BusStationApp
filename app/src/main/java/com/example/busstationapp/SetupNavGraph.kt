package com.example.busstationapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.busstationapp.presentation.MapScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MapScreen.route
    ) {
        composable(
            route = Screen.MapScreen.route
        ) {
            MapScreen()
        }
    }
}