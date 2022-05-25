package com.example.weatherapp.navigation

sealed class Screens(val route: String) {
    object MainScreen: Screens("main")
    object NewFindCityScreen: Screens("search")
    object NotAvailablePermission: Screens("no_permission")
    object GetPermission: Screens("get_permission")
}