package com.example.weatherapp.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.fragement.WeatherDetailDialogFragment
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.viewmodel.WeatherListViewModel

class WeatherListActivity : AppCompatActivity(), WeatherAdapter.WeatherIemClickListener {

    companion object {
        const val CITY_NAME = "CITY_NAME"
    }

    private val TAG = WeatherListActivity::class.java.simpleName

    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var errorView: TextView
    private lateinit var loadingLayout: LinearLayout
    private lateinit var loading: LottieAnimationView

    private var dataList: ArrayList<WeatherList> = ArrayList()
    private lateinit var weatherRecycleListViewAdapter: WeatherAdapter
    private lateinit var weatherListViewModel: WeatherListViewModel

    private var cityName: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        cityName = intent?.getStringExtra(CITY_NAME)
        weatherRecyclerView = findViewById(R.id.weather_list)
        errorView = findViewById(R.id.error_view)
        loadingLayout = findViewById(R.id.loading_layout)
        loading = findViewById(R.id.loading)
        loading.playAnimation()
        loadingLayout.visibility = View.VISIBLE
        weatherListViewModel = ViewModelProviders.of(this).get(WeatherListViewModel::class.java)
        weatherListViewModel.init(this, cityName)
        setRecyclerView(dataList)
        subscribeDataCallBack(false)
    }

    private fun setRecyclerView(dataList: ArrayList<WeatherList>) {
        weatherRecycleListViewAdapter =
            WeatherAdapter(this, this)
        val categoryLinearLayoutManager = LinearLayoutManager(this)
        categoryLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        weatherRecyclerView.layoutManager = categoryLinearLayoutManager
        weatherRecyclerView.adapter = weatherRecycleListViewAdapter
        weatherRecycleListViewAdapter.setAppList(dataList, false)
    }

    private fun subscribeDataCallBack(refreshNeeded: Boolean) {
        weatherListViewModel.getWeatherList()?.observe(this, Observer {
            if (it != null && it.size > 0) {
                weatherRecyclerView.visibility = View.VISIBLE
                loadingLayout.visibility = View.GONE
                loading.pauseAnimation()
                errorView.visibility = View.GONE
                weatherRecycleListViewAdapter.setAppList(it, refreshNeeded)
            } else {
                handleErrorView(getString(R.string.something_went_wrong))
            }
        })

        weatherListViewModel.getErrorLiveData().observe(this, Observer {
            handleErrorView(it)
        })
    }

    override fun onWeatherItemClick(weatherList: WeatherList) {
        val weatherDetailFragment = WeatherDetailDialogFragment.newInstance(weatherList)
        weatherDetailFragment.show(supportFragmentManager, WeatherDetailDialogFragment.TAG)
    }

    private fun handleErrorView(errorMessage: String) {
        weatherRecyclerView.visibility = View.GONE
        loadingLayout.visibility = View.GONE
        errorView.visibility = View.VISIBLE
        errorView.text = errorMessage
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }
}