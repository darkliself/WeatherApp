package com.example.weatherapp.navigation

sealed class Screens(val route: String) {
    object MainScreen: Screens("main")
    object NewFindCityScreen: Screens("search")
}