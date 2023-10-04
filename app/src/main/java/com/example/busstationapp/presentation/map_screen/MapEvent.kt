package com.example.busstationapp.presentation.map_screen

sealed interface MapEvent {

    data class OnMarkerClick(
        val markerId: Int,
    ): MapEvent
    object OnMapClick: MapEvent
    object ListTripButtonClick: MapEvent
    object NavigatedWithMarkerId: MapEvent
}