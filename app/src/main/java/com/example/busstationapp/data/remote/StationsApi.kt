package com.example.busstationapp.data.remote

import retrofit2.http.GET

interface StationsApi {

    @GET("case-study/6/stations/")
    suspend fun getStations(): BusStationResponseModel

    companion object {
        const val BASE_URL = "https://demo.voltlines.com/"
    }
}