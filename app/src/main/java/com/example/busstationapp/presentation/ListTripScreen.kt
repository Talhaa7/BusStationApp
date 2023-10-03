package com.example.busstationapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.busstationapp.presentation.model.MapUiModel

@Composable
fun ListTripScreen(
    sharedState: MapUiModel,
    viewModel: ListTripViewModel = hiltViewModel()

) {
    val stationId : String by viewModel.sharedState.collectAsStateWithLifecycle()

    val busStationInfo = sharedState.busStations.firstOrNull {
        it.id == stationId.toInt()
    }?.trips

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (busStationInfo != null) {
                items(busStationInfo) { item ->
                    ListTripItem(item)

                }
            }


        }
    }
}