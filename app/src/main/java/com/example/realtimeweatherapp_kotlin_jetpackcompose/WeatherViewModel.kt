package com.example.realtimeweatherapp_kotlin_jetpackcompose

import android.R.attr.value
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimeweatherapp_kotlin_jetpackcompose.api.Constant
import com.example.realtimeweatherapp_kotlin_jetpackcompose.api.NetworkResponse
import com.example.realtimeweatherapp_kotlin_jetpackcompose.api.RetrofitInstance
import com.example.realtimeweatherapp_kotlin_jetpackcompose.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi

    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()

    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult


    fun getData(city: String)
    {
        viewModelScope.launch {
            _weatherResult.value = NetworkResponse.Loading
            try {
                val response = weatherApi.getWeather( Constant.apiKey,city)

                if(response.isSuccessful)
                {
                    response.body()?.let{
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }
                else{
                    _weatherResult.value = NetworkResponse.Error("Failed to fetch data")
                }
            }
            catch (e : Exception)
            {
                _weatherResult.value = NetworkResponse.Error("Failed to fetch data")
            }


        }

    }

}