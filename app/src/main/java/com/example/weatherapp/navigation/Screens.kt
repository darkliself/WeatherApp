package com.example.weatherapp.navigation

sealed class Screens(val route: String) {
    object Main: Screens("main")
    object Weather: Screens("weather")
}