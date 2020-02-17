package com.example.weatherapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.activity.repository.ConnectivityManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cityNameEditText = findViewById<TextInputEditText>(R.id.edittext_city_name)
        val searchButton = findViewById<Button>(R.id.search_city_weather_button)
        searchButton.setOnClickListener {
            val cityName = cityNameEditText.text.toString()
            if (ConnectivityManager.isInternetAvailable(this)) {
                startWeatherListActivity(cityName)
            } else {
                Toast.makeText(
                    this,
                    getText(R.string.internet_not_available),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }

        cityNameEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                startWeatherListActivity(cityNameEditText.text.toString())
            }
            false
        }
    }

    private fun startWeatherListActivity(cityName: String) {
        if (cityName.isNotEmpty()) {
            val intent = Intent(this, WeatherListActivity::class.java)
            intent.putExtra(WeatherListActivity.CITY_NAME, cityName)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
        } else {
            Toast.makeText(this, getString(R.string.invalid_input), Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.press_back_twice), Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            delay(2500)
        }
    }
}
