package com.example.busstationapp.presentation.list_trip_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busstationapp.data.domain.BusStationRepository
import com.example.busstationapp.navigation.BUS_STATION_ID
import com.example.busstationapp.presentation.model.ListTripUiModel
import com.example.busstationapp.presentation.model.MapUiModel
import com.example.busstationapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTripViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: BusStationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListTripUiModel())
    val state = _state.asStateFlow()

    //private val _navigateState = MutableStateFlow(false)
    //val navigateState = _navigateState.asStateFlow()

    init {
        val argument = savedStateHandle.get<String>(BUS_STATION_ID).orEmpty()
        _state.update {
            it.copy(
                stationId = argument
            )
        }
    }

    private fun bookTrip(tripId: Int) {
        viewModelScope.launch {
            repository.postTripBook(
                _state.value.stationId,
                tripId.toString()
            ).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                navigateBack = true
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                showDialog = true
                            )
                        }
                    }
                    else -> {
                    }
                }
            }
        }
    }

    fun onEvent(event: ListTripEvent) {
        when (event) {
            is ListTripEvent.ClickBook -> {
                bookTrip(event.tripId)
            }
            ListTripEvent.DismissDialog -> {
                _state.update {
                    it.copy(
                        showDialog = false
                    )
                }
            }
            ListTripEvent.NavigatedBack -> {
                _state.update {
                    it.copy(
                        navigateBack = false
                    )
                }
            }
        }
    }

    fun initTrips(uiModel: MapUiModel) {

        val busStationInfo = uiModel.busStations.firstOrNull {
            it.id == _state.value.stationId.toInt()
        }?.trips ?: emptyList()

        _state.update {
            it.copy(
                tripList = busStationInfo
            )
        }
    }
}