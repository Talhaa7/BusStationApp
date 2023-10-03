package com.example.busstationapp.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busstationapp.BUS_STATION_ID
import com.example.busstationapp.data.domain.BusStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTripViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: BusStationRepository
): ViewModel(){

    private val _sharedState = MutableStateFlow("")
    val sharedState = _sharedState.asStateFlow()

    init {

        val argument = savedStateHandle.get<String>(BUS_STATION_ID).orEmpty()
        _sharedState.value = argument
        viewModelScope.launch {
            repository.postTripBook("1","210")

        }

    }


}