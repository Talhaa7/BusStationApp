package com.example.busstationapp.presentation.list_trip_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
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
    val state : ListTripUiModel by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    )
    {

        Text(
            style =
            TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
            text = "Trips"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            items(state.tripList) { item ->

                ListTripItem(item, navController = navController) {
                    viewModel.onEvent(ListTripEvent.ClickBook(it))
                }

                Divider()
            }
        }
    }

    if (state.showDialog) {

        MinimalDialog {
            viewModel.onEvent(ListTripEvent.DismissDialog)
        }

    }

    if (state.navigateBack) {
        viewModel.onEvent(ListTripEvent.NavigatedBack)
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set("station_id", state.stationId)
        navController.popBackStack()
    }

}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(3),

        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "The trip you selected is full.",
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Please select another one",
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { onDismissRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) {
                    Text("Select a Trip")
                }
            }
        }
    }
}