package com.example.weatherapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment

class WeatherListFragment : Fragment() {

    companion object {
        const val CITY_NAME = "CITY_NAME"
        val TAG = WeatherListFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(cityName: String) =
            WeatherListFragment().apply {
                arguments = Bundle().apply {
                    putString(CITY_NAME, cityName)
                }
            }
    }
}