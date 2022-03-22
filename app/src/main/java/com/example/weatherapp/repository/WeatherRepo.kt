package com.example.weatherapp.repository

import com.example.weatherapp.api.openweather.WeatherApiInterface
import com.example.weatherapp.di.Constants
import com.example.weatherapp.model.*
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherService: WeatherApiInterface
) {
    suspend fun getWeather(): Weather {
        return weatherService.getWeather(50.0, 36.25, units = Constants.UNITS, appid = Constants.API_KEY)
    }
    suspend fun getCurrentWeather(): Current {
        return weatherService.getWeather(50.0, 36.25, units = Constants.UNITS, appid = Constants.API_KEY).current
    }
    suspend fun getHourlyWeather(): List<Hourly> {
        return weatherService.getWeather(50.0, 36.25, units = Constants.UNITS, appid = Constants.API_KEY).hourly
    }
    suspend fun getDailyWeather(): List<Daily> {
        return weatherService.getWeather(50.0, 36.25, units = Constants.UNITS, appid = Constants.API_KEY).daily
    }
}
