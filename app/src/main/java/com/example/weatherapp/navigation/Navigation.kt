package com.example.weatherapp.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.repository.DataStoreRepo
import com.example.weatherapp.ui.screens.main.MainScreen
import com.example.weatherapp.ui.screens.search.SearchScreen
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
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreens(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.MainScreen.route
        ) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.NewFindCityScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}
