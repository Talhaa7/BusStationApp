package com.example.busstationapp.presentation.list_trip_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.busstationapp.data.remote.Trip

@Composable
fun ListTripItem(
    item: Trip,
    viewModel: ListTripViewModel = hiltViewModel(),
    navController: NavController,
    onBookClick: (tripId: Int) -> Unit
    ) {
    //val navigateState: Boolean by viewModel.navigateState.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Text(text = item.bus_name,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }

        Text(text = item.time)

        Button(
            onClick = {
                onBookClick(item.id)
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("station_id", item.id.toString())
                navController.popBackStack()
            },

        ) {
            Text(text = "Book")
        }
    }
}