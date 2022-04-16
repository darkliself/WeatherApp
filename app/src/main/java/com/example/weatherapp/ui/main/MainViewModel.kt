package com.example.weatherapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.*
import com.example.weatherapp.data.model.city.CityItem
import com.example.weatherapp.repository.DataStoreRepo
import com.example.weatherapp.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val dataStore = DataStoreRepo(context)
    private val _state = MutableStateFlow(emptyList<FullWeather>())
    val state: MutableStateFlow<List<FullWeather>>
        get() = _state
    private val _info = MutableStateFlow(emptyList<String>())
    val info: MutableStateFlow<List<String>>
        get() = _info
    init {
        viewModelScope.launch {
            if (dataStore.count() > 0) {
                val lat = dataStore.getLat()!!
                val lon = dataStore.getLon()!!
                val weather = weatherRepo.getWeather(lat, lon)
                _state.value = listOf(weather)
                _info.value = dataStore.getCityInfo()
            }
        }
    }

    suspend fun findCity(cityName: String): List<CityItem> {
            return weatherRepo.findCity(cityName)
    }

//    suspend fun getCurrent2(): List<FullWeather.Hourly> {
//
//        return weatherRepo.getHourlyWeather()
//    }
}