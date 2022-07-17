package com.example.sampledaggerexamples.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampledaggerexamples.Repository.WeatherRepository
import com.example.sampledaggerexamples.model.Response
import com.example.sampledaggerexamples.model.Weather


class WeatherViewModel (private val weatherRepository : WeatherRepository) : ViewModel() {

    val TAG  : String = " WeatherViewModel"
    var weatherDetails: MutableLiveData<Response<Weather>>? = null

    fun getWeatherDetails() : LiveData<Response<Weather>>? {
        weatherDetails = weatherRepository.weatherData
        Log.d(TAG, weatherDetails.toString())
        return weatherDetails
    }

    override fun onCleared() {
        weatherRepository.dispose()
        super.onCleared()
    }
}