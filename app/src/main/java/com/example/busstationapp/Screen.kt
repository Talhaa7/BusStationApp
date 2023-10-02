package com.example.busstationapp

sealed class Screen(val route: String) {
    object MapScreen: Screen("map_screen")
    object ListTripScreen: Screen("list_trip_screen")
}
