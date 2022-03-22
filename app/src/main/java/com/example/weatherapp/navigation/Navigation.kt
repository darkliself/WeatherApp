package com.example.weatherapp.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.MainScreen
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
    NavHost(navController = navController, startDestination = Screens.Weather.route) {

        composable(route = Screens.Main.route) {
            MainScreen()
        }
        composable(route = Screens.Weather.route) {
            WeatherScreen()
        }

    }
}