package com.example.weatherapp.api.openweather

import com.example.weatherapp.model.Weather
import dagger.Binds
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiInterface {
    @GET("onecall")
    // 50.0, 36.25, units = Constans.UNITS, appid = Constans.API_KEY
    suspend fun getWeather(
        @Query("lat") lat: Double ,
        @Query("lon") lon: Double,
        // metric|imperial|standard
        @Query("units") units: String,
        // values minutely|hourly|daily|current|alerts
        // @Query("exclude") exclude: String = "",
        @Query("appid") appid: String
    ): Weather
}

