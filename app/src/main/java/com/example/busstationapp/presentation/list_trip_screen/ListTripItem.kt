package com.example.busstationapp.presentation.list_trip_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.busstationapp.data.remote.Trip

@Composable
fun ListTripItem(
    item: Trip,
    onBookClick: (tripId: Int) -> Unit
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(modifier = Modifier
            .weight(1f),
            text = item.bus_name,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Text(text = item.time)

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            onClick = {
                onBookClick(item.id)
            },

        ) {
            Text(text = "Book")
        }
    }
}