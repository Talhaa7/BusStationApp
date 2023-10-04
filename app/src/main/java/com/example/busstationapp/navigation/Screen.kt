package com.example.busstationapp.navigation

const val BUS_STATION_ID = "id"

sealed class Screen(val route: String) {
    object MapScreen: Screen("map_screen")
    object ListTripScreen: Screen("list_trip_screen/{$BUS_STATION_ID}") {
        fun passBusStationId(id: String): String {
            return "list_trip_screen/$id"
        }
    }
}
