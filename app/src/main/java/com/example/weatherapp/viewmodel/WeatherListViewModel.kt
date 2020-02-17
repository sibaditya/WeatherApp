package com.example.weatherapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.activity.repository.ConnectivityManager
import com.example.weatherapp.model.Example
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.util.UrlUtils

class WeatherListViewModel : ViewModel(), ConnectivityManager.FetchNetworkResponse {
    private lateinit var weatherlist: MutableLiveData<List<WeatherList>>
    private lateinit var errorMessage: MutableLiveData<String>
    private lateinit var connectivityManager: ConnectivityManager

    fun init(ctx: Context?, cName: String?) {
        weatherlist = MutableLiveData()
        errorMessage = MutableLiveData()
        connectivityManager = ConnectivityManager(this, ctx)
        connectivityManager.fetchFeedFromNetwork(
            UrlUtils.getFiveDayForcastURL(cName),
            Example::class.java
        )
    }

    fun getWeatherList(): MutableLiveData<List<WeatherList>> {
        return weatherlist;
    }

    fun getErrorLiveData(): MutableLiveData<String> {
        return errorMessage;
    }

    override fun <T> onSuccess(modelList: List<T>?) {
        val weatherList = modelList as List<Example>
        weatherlist.postValue(weatherList[0].list)
    }

    override fun onFailure(msg: String?) {
        errorMessage.postValue(msg)
    }
}