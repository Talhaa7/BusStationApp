package com.example.busstationapp.presentation.model

data class MapUiModel(
    val busStations : List<BusStationMapUiModel> = emptyList(),
    val selectedMarkerId: Int? = null,
    val navigateWithMarkerId : Int? = null,
)
