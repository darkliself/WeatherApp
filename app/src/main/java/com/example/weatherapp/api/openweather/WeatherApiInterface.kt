package com.example.weatherapp.api.openweather

import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.city.CityItem
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiInterface {
    @GET("data/2.5/onecall")
    // 50.0, 36.25, units = Constans.UNITS, appid = Constans.API_KEY
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        // metric|imperial|standard
        @Query("units") units: String,
        // values minutely|hourly|daily|current|alerts
        // @Query("exclude") exclude: String = "",
        @Query("appid") appid: String
    ): Weather

    // http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid=76bb42ac7cb89fc255ba962c7916dd20
    @GET("geo/1.0/direct")
    suspend fun findCity(
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("appid") appid: String
    ): List<CityItem>
}

