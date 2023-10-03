package com.example.busstationapp.presentation.model

import com.example.busstationapp.data.remote.Trip

data class ListTripUiModel(
    val tripList: List<Trip> = emptyList(),
    val showDialog: Boolean = false,
    val stationId: String = ""
)
