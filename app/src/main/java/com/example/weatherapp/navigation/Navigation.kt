package com.example.weatherapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val materialBlue700 = Color(0xFF1976D2)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(

        scaffoldState = scaffoldState,
        topBar = {
            TopBarNav(navController)
        },
        drawerContent = { Text(text = "drawerContent") },
        content = {
            NavigationScreens(navController)
        },
        //        bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreens(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Weather.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen()
        }
        composable(route = Screens.FindCity.route) {
            FindCityScreen(navController = navController)
        }
        composable(route = Screens.Weather.route) {
            WeatherScreen()
        }

        composable(route = Screens.ForecastScreen.route) {
            ForecastScreen()
        }
        composable(route = Screens.PrecipitationScreen.route) {
            PrecipitationScreen()
        }

    }
}

@Composable
fun Today() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Today")
    }

}
