package com.example.weatherapp.activity.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.R
import com.example.weatherapp.application.AppController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.ArrayList
import java.util.HashMap

class ConnectivityManager(
    /**
     * [FetchNetworkResponse] callback listener object
     */
    private val dataResponse: FetchNetworkResponse,
    /**
     * Context
     */
    private val mContext: Context?
) {
    /**
     * Interface for callback
     */
    interface FetchNetworkResponse {
        fun <T> onSuccess(modelList: List<T>?)
        fun onFailure(msg: String?)
    }

    /**
     * TAG for logs
     */
    private val TAG = javaClass.simpleName
    private var gson: Gson? = null

    /**
     * Method to fetch feed from the network
     */
    fun <T> fetchFeedFromNetwork(url: String, modelClass: Class<T>) {
        Log.d(TAG, "fetchFeedFromNetwork")
        Log.d(TAG, "url = $url")
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(mContext)
        // Request a string response from the provided URL.
        val stringRequest: StringRequest = object : StringRequest(Method.GET, url,
            Response.Listener { response ->
                Log.d(TAG, "on Success")
                handleResponse(response, modelClass)
            }, Response.ErrorListener { dataResponse.onFailure(mContext?.getString(R.string.city_not_found)) }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                return HashMap()
            }

            override fun getParams(): Map<String, String> {
                return HashMap()
            }
        }
        // Add the request to the RequestQueue.
        Log.d("StringRequest", stringRequest.url)
        AppController.instance?.addToRequestQueue(stringRequest)
    }

    /**
     * Method to handle the response data either fetched from network or cache
     *
     * @param response String
     */
    private fun <T> handleResponse(response: String, modelClass: Class<T>) {
        Log.d(TAG, "handle response")
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("M/d/yy hh:mm a")
        val gson: Gson = gsonBuilder.create()
        val modelList: List<T> = ArrayList(listOf(gson.fromJson(response, modelClass)))
        dataResponse.onSuccess(modelList)
    }

    companion object {
        /**
         * Method to check the availability of internet
         *
         * @param context [Context]
         * @return boolean
         */
        fun isInternetAvailable(context: Context?): Boolean {
            val cm = (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }

}