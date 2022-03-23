package com.example.weatherapp.navigation

sealed class Screens(val route: String) {
    object FindCity: Screens("find_city")
    object Weather: Screens("weather")
}