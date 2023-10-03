package com.example.busstationapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.busstationapp.data.remote.Trip

@Composable
fun ListTripItem(item: Trip) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        // Sol tarafta alt alta iki metin
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

        val showAlertMessage = remember {
            mutableStateOf(false)
        }

        if (showAlertMessage.value) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showAlertMessage.value = false },
                title = { Text(text = "The trip you selected is full.")},
                text = { Text(text = "Please select another one")},
                confirmButton = {
                    Button(onClick = { showAlertMessage.value = false }) {
                        
                        Text(text = "Select a Trip")

                    }
                })
        }

        // Sağ tarafta bir düğme
        Button(
            onClick = {
                showAlertMessage.value = true
            },

        ) {
            Text(text = "Book")
        }
    }
}