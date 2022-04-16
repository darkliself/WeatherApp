package com.example.weatherapp.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.repository.DataStoreRepo
import com.example.weatherapp.ui.search.NewFindCityScreen
import com.example.weatherapp.ui.screens.*
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val dataStore = DataStoreRepo(LocalContext.current)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf("")}
    scope.launch {
        startDestination = if (dataStore.count() > 0) {
            Screens.MainScreen.route
        } else {
            Screens.NewFindCityScreen.route
        }
    }

    if (startDestination !== "") {
        NavigationScreens(navController,startDestination)
    }


    // val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))


}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreens(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
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
        composable(route = Screens.NewFindCityScreen.route) {
            NewFindCityScreen(navController = navController)
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
