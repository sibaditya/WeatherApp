package com.example.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        const val DATE_FORMAT_MMM_DD = "MMM dd"
        const val DATE_FORMAT_DD_MMM_YYYY = "dd MMM yyyy"

        fun getTemperatureInCelsius(temp: Float?): Int? {
            return (temp?.minus(273.15F))?.toInt()
        }

        fun getTime(dateString: String?): String {
            // obtain date and time from initial string
            val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ROOT).parse(dateString)

            // set time string
            return SimpleDateFormat("hh:mm a", Locale.ROOT).format(date)
        }

        fun getDate(dateString: String?, dateFormat: String): String {
            // obtain date and time from initial string
            val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ROOT).parse(dateString)
            // set date string
            return SimpleDateFormat(dateFormat, Locale.ROOT).format(date)
                .toUpperCase(Locale.ROOT)
        }
    }
}