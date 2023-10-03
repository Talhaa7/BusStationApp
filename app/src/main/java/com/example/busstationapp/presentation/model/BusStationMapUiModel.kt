package com.example.busstationapp.presentation.model

import com.example.busstationapp.data.remote.Trip
import com.google.android.gms.maps.model.LatLng

data class BusStationMapUiModel(
    val latLng: LatLng? = null,
    val tripCount: String? = null,
    val trips: List<Trip>? = emptyList(),
    val id: Int? = null
)
