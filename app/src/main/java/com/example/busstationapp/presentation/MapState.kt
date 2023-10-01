package com.example.busstationapp.presentation

import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    val isFalloutMap: Boolean = false
)
