package com.example.busstationapp.presentation.map_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.busstationapp.R
import com.example.busstationapp.navigation.Screen
import com.example.busstationapp.presentation.model.MapUiModel
import com.example.busstationapp.utils.bitmapDescriptorFromVector
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(
    viewModel: MapsViewModel = hiltViewModel(),
    navController: NavController,
    stationId: String? = null
) {
    val coordinates = LatLng(41.09297645004368, 29.003123581510543)
    val state: MapUiModel by viewModel.state.collectAsStateWithLifecycle()

    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = true
        )
    }

    if (stationId != null) {
        viewModel.onEvent(MapEvent.OnCompletedWithStationId(stationId.toInt()))
    }

    val pointIcon = remember {
        mutableStateOf<BitmapDescriptor?>(null)
    }
    val selectedPointIcon = remember {
        mutableStateOf<BitmapDescriptor?>(null)
    }
    val completedPoint = remember {
        mutableStateOf<BitmapDescriptor?>(null)
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        pointIcon.value = bitmapDescriptorFromVector(
            context,
            R.drawable.point
        )
    })

    LaunchedEffect(key1 = Unit, block = {
        selectedPointIcon.value = bitmapDescriptorFromVector(
            context,
            R.drawable.selected_point
        )
    })

    LaunchedEffect(key1 = Unit, block = {
        completedPoint.value = bitmapDescriptorFromVector(
            context,
            R.drawable.completed
        )
    })


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = uiSettings,
            onMapClick = {
                viewModel.onEvent(MapEvent.OnMapClick)
            },
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition(coordinates,10f,0f,0f)
            }
        ) {
            state.busStations.forEach { uiModel ->
                uiModel.latLng?.let { it1 ->

                    Marker(
                        state = MarkerState(position = it1),
                        title = "${uiModel.tripCount} Trips",
                        onClick = {
                            it.showInfoWindow()
                            uiModel.id?.let {
                                viewModel.onEvent(MapEvent.OnMarkerClick(uiModel.id))
                            }
                            true
                        },
                        icon = if (uiModel.isBookCompleted) {
                            completedPoint.value
                        } else if (uiModel.id == state.selectedMarkerId) {
                            selectedPointIcon.value
                        } else {
                            pointIcon.value
                        },
                    )
                }
            }
        }

        if (state.selectedMarkerId != null) {
            Button(
                onClick = {
                    navController.navigate(
                        route = Screen.ListTripScreen.passBusStationId(
                            state.selectedMarkerId.toString()
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text("List Trips")
            }
        }
    }
}