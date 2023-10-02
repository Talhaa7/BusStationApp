package com.example.busstationapp

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
class MainViewModel @Inject constructor(
    private val repository: BusStationRepository
): ViewModel(){

    private val _state = MutableStateFlow(MapUiModel())
    val state = _state.asStateFlow()

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

}