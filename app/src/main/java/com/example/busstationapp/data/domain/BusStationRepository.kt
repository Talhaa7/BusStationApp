package com.example.busstationapp.data.domain

import com.example.busstationapp.data.remote.Trip
import com.example.busstationapp.presentation.model.BusStationMapUiModel
import com.example.busstationapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface BusStationRepository {

    suspend fun getStations(): Flow<Resource<List<BusStationMapUiModel>>>

    suspend fun postTripBook(stationId: String, tripId: String): Flow<Resource<Trip>>
}