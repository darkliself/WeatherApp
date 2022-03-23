package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.navigation.Navigation
import com.example.weatherapp.ui.main.MainViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
//            WeatherAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {

//                }
//            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting() {
    val scope = rememberCoroutineScope()
    val homeViewModel = viewModel(modelClass = MainViewModel::class.java)
    val state by homeViewModel.state.collectAsState()
    var state2 by remember { mutableStateOf(emptyList<Weather>())}

    scope.launch {
        state2 = homeViewModel.state.value
    }
    Column() {
        if (state2.isEmpty()) {
           CircularProgressIndicator(
               Modifier.fillMaxSize()
           )
        } else {
            Text(text = state2.toString())
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}