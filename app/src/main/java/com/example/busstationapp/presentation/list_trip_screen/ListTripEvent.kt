package com.example.busstationapp.presentation.list_trip_screen

sealed interface ListTripEvent {
    data class ClickBook(val tripId: Int): ListTripEvent
    object DismissDialog: ListTripEvent

    object NavigatedBack: ListTripEvent
}