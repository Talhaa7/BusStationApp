package com.example.busstationapp.presentation.list_trip_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.busstationapp.presentation.model.ListTripUiModel
import com.example.busstationapp.presentation.model.MapUiModel

@Composable
fun ListTripScreen(
    sharedState: MapUiModel,
    viewModel: ListTripViewModel = hiltViewModel(),
    navController: NavController

) {

    viewModel.initTrips(sharedState)
    val state : ListTripUiModel by viewModel.state.collectAsStateWithLifecycle()
    //val navigateState: Boolean by viewModel.navigateState.collectAsStateWithLifecycle()

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
            items(state.tripList) { item ->

                ListTripItem(item, navController = navController) {
                    viewModel.onEvent(ListTripEvent.ClickBook(it))
                }
            }
        }
    }

    if (state.showDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { viewModel.onEvent(ListTripEvent.DismissDialog) },
            title = { Text(text = "The trip you selected is full.") },
            text = { Text(text = "Please select another one") },
            confirmButton = {
                Button(onClick = { viewModel.onEvent(ListTripEvent.DismissDialog) }) {
                    Text(text = "Select a Trip")
                }
            }
        )
    }

    /*if (state.navigateBack) {
        viewModel.onEvent(ListTripEvent.NavigatedBack)
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set("station_id", state.stationId)
        navController.popBackStack()
    }*/
}