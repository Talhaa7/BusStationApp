package com.example.busstationapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busstationapp.data.domain.BusStationRepository
import com.example.busstationapp.presentation.model.MapUiModel
import com.example.busstationapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: BusStationRepository
): ViewModel() {

    private val _state = MutableStateFlow(MapUiModel())
    val state = _state.asStateFlow()

    //private val _showButtonState = MutableStateFlow(Boolean)


    init {
        getStations()
    }
    fun getStations() {
        viewModelScope.launch {
            repository.getStations().collect { resource ->
                when(resource) {
                    is Resource.Success -> {
                        resource.data?.let { response->
                            _state.update { uiModel ->
                                uiModel.copy(
                                    busStations = response
                                )

                            }
                        }
                    }

                    else -> {
                    }
                }
            }
        }

    }

    fun onEvent(event: MapEvent) {
        when(event) {
            MapEvent.ListTripButtonClick -> {
                _state.update {
                    it.copy(
                        navigateWithMarkerId = it.selectedMarkerId
                    )
                }

            }

            MapEvent.OnMapClick -> {
                _state.update {
                    it.copy(
                        selectedMarkerId = null
                    )
                }
            }

            is MapEvent.OnMarkerClick -> {
                _state.update {
                    it.copy(
                        selectedMarkerId = event.markerId
                    )
                }
            }

            MapEvent.NavigatedWithMarkerId -> {
                _state.update {
                    it.copy(
                        navigateWithMarkerId = null
                    )
                }
            }
        }
    }
}