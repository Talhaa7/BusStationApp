package com.example.busstationapp

sealed interface ListTripEvent {
    data class ClickBook(val tripId: Int): ListTripEvent
    object DismissDialog: ListTripEvent
}