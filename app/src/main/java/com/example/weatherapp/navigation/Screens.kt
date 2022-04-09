package com.example.weatherapp.navigation

sealed class Screens(val route: String) {
    object FindCity: Screens("find_city")
    object Weather: Screens("weather")
    object MainScreen: Screens("main")
    object ForecastScreen: Screens("forecast")
    object PrecipitationScreen: Screens("precipitation")
    object NewFindCityScreen: Screens("new_find_city")
}