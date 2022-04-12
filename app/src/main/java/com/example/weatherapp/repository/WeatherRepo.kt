package com.example.weatherapp.repository

import com.example.weatherapp.api.openweather.WeatherApiInterface
import com.example.weatherapp.di.Constants
import com.example.weatherapp.data.model.*
import com.example.weatherapp.data.model.city.CityItem
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherService: WeatherApiInterface
) {
    suspend fun getWeather(lat: String, lon: String): FullWeather {
        return weatherService.getWeather(
            lat,
            lon,
            units = Constants.UNITS,
            appid = Constants.API_KEY
        )
    }

    suspend fun findCity(cityName: String): List<CityItem> {
        return weatherService.findCity(cityName, 5, Constants.API_KEY)
    }

    suspend fun getHourlyWeather(lat: String, lon: String): List<FullWeather.Hourly> {
        return weatherService.getWeather(
            lat,
            lon,
            units = Constants.UNITS,
            appid = Constants.API_KEY
        ).hourly
    }

    suspend fun getDailyWeather(lat: String, lon: String): List<FullWeather.Daily> {
        return weatherService.getWeather(
            lat,
            lon,
            units = Constants.UNITS,
            appid = Constants.API_KEY
        ).daily
    }
}
