package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.Weather
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

    private val _state = MutableStateFlow(emptyList<Weather>())
    val state: StateFlow<List<Weather>>
        get() = _state

    init {
        viewModelScope.launch {
            val weather = weatherRepo.getWeather()
            _state.value = listOf(weather)
        }
    }

    suspend fun findCity(cityName: String): List<CityItem> {
            return weatherRepo.findCity(cityName)
    }
}