package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.data.model.Current
import com.example.weatherapp.data.model.Hourly
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.ui.main.MainViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.Clock
import java.time.LocalDate
import java.util.*


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ForecastScreen() {
    val scope = rememberCoroutineScope()
    val homeViewModel = hiltViewModel<MainViewModel>()

    var z by remember { mutableStateOf("") }
    scope.launch {
        val state = homeViewModel.getCurrent()
        z = state.toString()
        println(state.toString())
    }
    var result by remember {
        mutableStateOf(emptyList<Hourly>())
    }
    scope.launch {
        result = homeViewModel.getCurrent2()

    }









    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF454857),
                        Color(0xFF2C2D35)
                    )
                )
            )
    ) {
        LazyColumn(
            Modifier.fillMaxSize()
        ) {
            item() {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (result.isEmpty()) {

                    } else {
                        Text(result[0].toString())
                    }

                }
            }
        }
    }
}
