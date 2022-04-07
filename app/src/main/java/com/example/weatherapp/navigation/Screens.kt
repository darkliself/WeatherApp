package com.example.weatherapp.navigation

sealed class Screens(val route: String) {
    object FindCity: Screens("find_city")
    object Weather: Screens("weather")
    object MainScreen: Screens("main_screen")
    object ForecastScreen: Screens("forecast_screen")
    object PrecipitationScreen: Screens("precipitation_screen")
}