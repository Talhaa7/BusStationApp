package com.example.busstationapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.maps.android.compose.MapUiSettings


@Composable
fun MapScreen(
    viewModel: MapsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
}