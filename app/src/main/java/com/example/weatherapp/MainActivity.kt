package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.navigation.Navigation
import com.example.weatherapp.ui.main.MainViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme() {
                // val navController = rememberNavController()
                Navigation()
            }
        }
    }
}



//
//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Navigation()
////            WeatherAppTheme {
////                // A surface container using the 'background' color from the theme
////                Surface(
////                    modifier = Modifier.fillMaxSize(),
////                    color = MaterialTheme.colors.background
////                ) {
//
////                }
////            }
//        }
//    }
//}
//
//@SuppressLint("CoroutineCreationDuringComposition")
//@Composable
//fun Greeting() {
//    val scope = rememberCoroutineScope()
//    val homeViewModel = viewModel(modelClass = MainViewModel::class.java)
//    val state by homeViewModel.state.collectAsState()
//    var state2 by remember { mutableStateOf(emptyList<Weather>())}
//
//    scope.launch {
//        state2 = homeViewModel.state.value
//    }
//    Column() {
//        if (state2.isEmpty()) {
//           CircularProgressIndicator(
//               Modifier.fillMaxSize()
//           )
//        } else {
//            Text(text = state2.toString())
//        }
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//
//}