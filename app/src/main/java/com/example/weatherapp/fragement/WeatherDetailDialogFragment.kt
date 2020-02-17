package com.example.weatherapp.fragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.util.Util
import com.example.weatherapp.util.Util.Companion.DATE_FORMAT_DD_MMM_YYYY

class WeatherDetailDialogFragment : DialogFragment() {
    companion object {
        const val WEATHER_DETAIL = "WEATHER_DETAIL"
        val TAG = WeatherDetailDialogFragment::class.java.simpleName

        fun newInstance(weatherDetail: WeatherList?) =
            WeatherDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(WEATHER_DETAIL, weatherDetail)
                }
            }
    }

    private var weatherList: WeatherList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weatherList = it.getParcelable(WEATHER_DETAIL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_weather_detail, container, false)
        val lottieAnimationView = view.findViewById<LottieAnimationView>(R.id.weather_detail_anim)
        lottieAnimationView.playAnimation()

        val dateTextView = view.findViewById<TextView>(R.id.tv_date)
        dateTextView.text = Util.getDate(weatherList?.dtTxt, DATE_FORMAT_DD_MMM_YYYY)

        val timeTextView = view.findViewById<TextView>(R.id.tv_time)
        timeTextView.text = Util.getTime(weatherList?.dtTxt)

        val actualTempTextView = view.findViewById<TextView>(R.id.tv_actual_temp)
        actualTempTextView.text = getString(R.string.weather_detail_actual_tempeature, Util.getTemperatureInCelsius(weatherList?.main?.temp))

        val feelsLikeTempTextView = view.findViewById<TextView>(R.id.tv_feels_temp)
        feelsLikeTempTextView.text = getString(R.string.feels_tempeature, Util.getTemperatureInCelsius(weatherList?.main?.feelsLike))

        val minTempTextView = view.findViewById<TextView>(R.id.tv_min_temp)
        minTempTextView.text = getString(R.string.min_tempeature, Util.getTemperatureInCelsius(weatherList?.main?.tempMin))


        val maxTempTextView = view.findViewById<TextView>(R.id.tv_max_temp)
        maxTempTextView.text = getString(R.string.max_tempeature, Util.getTemperatureInCelsius(weatherList?.main?.tempMax))

        val weatherTextView = view.findViewById<TextView>(R.id.tv_weather)
        val weather = weatherList?.weather?.get(0)
        weatherTextView.text = "${weather?.main} (${weather?.description})"

        return view
    }
}