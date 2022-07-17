package com.example.sampledaggerexamples.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampledaggerexamples.Repository.WeatherRepository
import javax.inject.Inject

class WeatherViewModelFactory @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }

}