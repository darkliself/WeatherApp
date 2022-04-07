package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.*
import com.example.weatherapp.data.model.city.CityItem
import com.example.weatherapp.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<FullWeather>())
    val state: MutableStateFlow<List<FullWeather>>
        get() = _state

    private var _tests  = MutableStateFlow(emptyList<FullWeather>())
    val tests: MutableStateFlow<List<FullWeather>>
        get() = _tests

    init {
        viewModelScope.launch {
            val weatherz = weatherRepo.getWeather()
            _state.value = listOf(weatherz)
        }
    }

    suspend fun findCity(cityName: String): List<CityItem> {
            return weatherRepo.findCity(cityName)
    }

    suspend fun getCurrent2(): List<FullWeather.Hourly> {

        return weatherRepo.getHourlyWeather()
    }
}