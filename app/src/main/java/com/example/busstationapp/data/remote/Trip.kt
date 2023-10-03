package com.example.busstationapp.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trip(
    val bus_name: String,
    val id: Int,
    val time: String
): Parcelable