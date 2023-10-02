package com.example.busstationapp.data.domain

import com.example.busstationapp.data.remote.StationsApi
import com.example.busstationapp.presentation.model.BusStationMapUiModel
import com.example.busstationapp.utils.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusStationRepositoryImp @Inject constructor(private val stationsApi: StationsApi): BusStationRepository{
    override suspend fun getStations(): Flow<Resource<List<BusStationMapUiModel>>> {
        return flow {
            val stationsList = stationsApi.getStations()
            emit(Resource.Success(
                data = stationsList.map {

                    val coordinates = it.center_coordinates.split(",").map {
                        it.toDouble()
                    }
                    val latLng = LatLng(coordinates[0],coordinates[1])
                    BusStationMapUiModel(
                        latLng,
                        it.trips_count.toString(),
                        it.trips
                    )
                }
            )
            )
        }
    }

}