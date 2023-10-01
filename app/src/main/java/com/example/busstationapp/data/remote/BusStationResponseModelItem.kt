package com.example.busstationapp.data.remote

data class BusStationResponseModelItem(
    val center_coordinates: String,
    val id: Int,
    val name: String,
    val trips: List<Trip>,
    val trips_count: Int
)