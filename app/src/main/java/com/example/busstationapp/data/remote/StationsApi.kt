package com.example.busstationapp.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StationsApi {

    @GET("case-study/6/stations/")
    suspend fun getStations(): List<BusStationResponseModelItem>

    companion object {
        const val BASE_URL = "https://demo.voltlines.com/"
    }

    @POST("case-study/6/stations/{station_id}/trips/{trip_id}")
    suspend fun postTripBook(
        @Path("station_id") stationId: String,
        @Path("trip_id") tripId: String
    ) : Trip
}