package com.example.realtimeweatherapp_kotlin_jetpackcompose

import android.util.Log
import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {

    fun getData(city: String)
    {
        Log.i("City", city)

    }

}