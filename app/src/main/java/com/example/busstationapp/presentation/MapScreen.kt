package com.example.busstationapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.busstationapp.Screen
import com.example.busstationapp.presentation.model.MapUiModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker


@Composable
fun MapScreen(
    viewModel: MapsViewModel = hiltViewModel(),
    navController: NavController
) {
    //val coordinates = LatLng(41.09297645004368,29.003123581510543)
    val state : MapUiModel by viewModel.state.collectAsStateWithLifecycle()

    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = false
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = uiSettings
        ) {
            state.busStations.forEach {
                it.latLng?.let { it1 ->
                    Marker(
                        position = it1,
                        title = "${it.tripCount} Trips"
                    )
                }
            }

        }

        Button(
            onClick = {
                      navController.navigate(route = Screen.ListTripScreen.passBusStationId("400"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("List Trips")
        }

    }
}