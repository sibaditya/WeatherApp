package com.example.weatherapp.util

class UrlUtils {
    companion object{
        private const val API_KEY = "327024727d036f4cd82405abea973ba4"
        private const val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q="
        private const val QUERY_PARAM = "&appid="+ API_KEY

        public fun getFiveDayForcastURL(cityName: String?): String {
            return BASE_URL + cityName + QUERY_PARAM
        }
    }
}