package com.example.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat")
    @Expose
    var lat: Float? = null,
    @SerializedName("lon")
    @Expose
    var lon: Float? = null
)