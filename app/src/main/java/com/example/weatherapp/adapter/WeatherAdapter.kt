package com.example.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.util.Util
import com.example.weatherapp.util.Util.Companion.DATE_FORMAT_MMM_DD
import java.text.SimpleDateFormat
import java.util.*


class WeatherAdapter(val context: Context?, val weatherIemClickListener: WeatherIemClickListener): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weatherList = ArrayList<WeatherList>()

    interface WeatherIemClickListener {
        fun onWeatherItemClick(weatherList: WeatherList)
    }

    fun setAppList(wList: List<WeatherList>, refreshNeeded: Boolean) {
        if (refreshNeeded) {
            weatherList.clear()
        }
        weatherList.addAll(wList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_weather_view, parent, false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherListItem = weatherList[position]
        holder.bind(weatherListItem, context, weatherIemClickListener)

    }


    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var weatherView: RelativeLayout? = null
        private var timeTextView: TextView? = null
        private var dateTextView: TextView? = null
        private var actualTempTextView: TextView? = null
        private var feelsLikeTextView: TextView? = null
        private var windSpeedTextView: TextView? = null

        init {
            weatherView = itemView.findViewById(R.id.weather_view)
            timeTextView = itemView.findViewById(R.id.time)
            dateTextView = itemView.findViewById(R.id.date)
            actualTempTextView = itemView.findViewById(R.id.actual_temp)
            feelsLikeTextView = itemView.findViewById(R.id.feels_like)
            windSpeedTextView = itemView.findViewById(R.id.wind_speed)
        }

        fun bind(
            weatherList: WeatherList,
            context: Context?,
            weatherIemClickListener: WeatherIemClickListener
        ) {
            timeTextView?.text = Util.getTime(weatherList.dtTxt)
            dateTextView?.text = Util.getDate(weatherList.dtTxt, DATE_FORMAT_MMM_DD)
            actualTempTextView?.text = context?.getString(
                R.string.actual_tempeature,
                Util.getTemperatureInCelsius(weatherList.main?.temp)
            )
            feelsLikeTextView?.text = context?.getString(
                R.string.feels_tempeature,
                Util.getTemperatureInCelsius(weatherList.main?.feelsLike)
            )
            windSpeedTextView?.text =
                context?.getString(R.string.wind_speed, weatherList.wind?.speed?.toInt())
            weatherView?.setOnClickListener {
                weatherIemClickListener.onWeatherItemClick(weatherList)
            }

        }
    }
}