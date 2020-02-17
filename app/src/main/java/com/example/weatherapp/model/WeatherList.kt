package com.example.weatherapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherList (
    @SerializedName("dt")
    @Expose
    var dt: Int? = null,

    @SerializedName("main")
    @Expose
    var main: Main? = null,

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null,

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null,

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null,

    @SerializedName("snow")
    @Expose
    var snow: Snow? = null,

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null,

    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null,

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        TODO("main"),
        TODO("weather"),
        TODO("clouds"),
        TODO("wind"),
        TODO("snow"),
        TODO("sys"),
        parcel.readString(),
        TODO("rain")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(dt)
        parcel.writeString(dtTxt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherList> {
        override fun createFromParcel(parcel: Parcel): WeatherList {
            return WeatherList(parcel)
        }

        override fun newArray(size: Int): Array<WeatherList?> {
            return arrayOfNulls(size)
        }
    }
}