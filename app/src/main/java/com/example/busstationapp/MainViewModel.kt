package com.example.busstationapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busstationapp.data.domain.BusStationRepository
import com.example.busstationapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BusStationRepository
): ViewModel(){


    fun getStations() {
        viewModelScope.launch {
            repository.getStations().collect { resource ->
                when(resource) {
                    is Resource.Success -> {
                        resource.data?.let {

                        }
                    }

                    else -> {
                    }
                }
            }
        }

    }

}