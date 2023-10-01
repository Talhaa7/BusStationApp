package com.example.busstationapp.data.domain

import com.example.busstationapp.data.remote.StationsApi
import com.example.busstationapp.presentation.model.BusStationMapUiModel
import com.example.busstationapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusStationRepositoryImp @Inject constructor(private val stationsApi: StationsApi): BusStationRepository{
    override suspend fun getStations(): Flow<Resource<List<BusStationMapUiModel>>> {
        return flow {
            val stationsList = stationsApi.getStations()
            emit(Resource.Success(
                data = stationsList.map {
                    BusStationMapUiModel(
                        it.center_coordinates,
                        it.center_coordinates,
                        it.trips.size.toString()+" Trips"
                    )
                }
            )
            )
        }
    }

}