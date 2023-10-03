package com.example.busstationapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.busstationapp.presentation.ListTripScreen
import com.example.busstationapp.presentation.MapScreen
import com.example.busstationapp.presentation.MapsViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MapScreen.route
    ) {
        composable(
            route = Screen.MapScreen.route,
        ) {
            MapScreen(navController = navController)
        }

        composable(
            route = Screen.ListTripScreen.route,
            arguments = listOf(navArgument(BUS_STATION_ID) {
                type = NavType.StringType
            })
        ) {entry ->
            val viewModel = entry.sharedViewModel<MapsViewModel>(navController)
            val state by viewModel.state.collectAsStateWithLifecycle()
            ListTripScreen(state)
        }
    }
}

@Composable
inline fun  <reified  T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}