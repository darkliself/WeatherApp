package com.example.weatherapp.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.FindCityScreen
import com.example.weatherapp.ui.screens.WeatherScreen

@Composable
fun Navigation() {
    val materialBlue700 = Color(0xFF1976D2)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text(text = "drawerContent") },
        content = {
            NavigationScreens()
        },
        //        bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
    )
}


@Composable
fun NavigationScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.FindCity.route) {

        composable(route = Screens.FindCity.route) {
            FindCityScreen(navController = navController)
        }
        composable(route = Screens.Weather.route) {
            WeatherScreen()
        }

    }
}