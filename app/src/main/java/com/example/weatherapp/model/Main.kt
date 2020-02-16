package com.example.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("temp")
    @Expose
    var temp: Float? = null,
    @SerializedName("feels_like")
    @Expose
    var feelsLike: Float? = null,
    @SerializedName("temp_min")
    @Expose
    var tempMin: Float? = null,
    @SerializedName("temp_max")
    @Expose
    var tempMax: Float? = null,
    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null,
    @SerializedName("sea_level")
    @Expose
    var seaLevel: Int? = null,
    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Int? = null,
    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null,
    @SerializedName("temp_kf")
    @Expose
    var tempKf: Float? = null
)