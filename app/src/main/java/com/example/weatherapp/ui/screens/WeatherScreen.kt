package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.WeatherDateFormat
import com.example.weatherapp.ui.main.MainViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WeatherScreen() {

    val scope = rememberCoroutineScope()

    //val homeViewModel = viewModel(modelClass = MainViewModel::class.java)
    val homeViewModel = hiltViewModel<MainViewModel>()
    val state by homeViewModel.state.collectAsState()
    val time by remember { mutableStateOf(mutableListOf<String>())}

    if (state.isEmpty()) {
    } else {
        state[0].hourly.forEach{
            time.add(WeatherDateFormat.getTime(it.dt, dateFormat = "hh:mm"))
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1C99DF),
                        Color(0xFF32B5FF).copy(0.26f)
                    )
                )
            )
    ) {
        // Header

        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text("20C")
            // Divider(color = Color.Black, thickness = 2.dp, modifier = Modifier.rotate(90f).border(width = 20.dp, Color.Black))

            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
            )
            Row(
                Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Roaming")
                Text("City Kharkov")
            }
        }

        Box(
            Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(0.5f)
                //.background(Color.Black)
                .align(BiasAlignment(0f, -0.3f))
                .zIndex(10f)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.cloud),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .align(BiasAlignment(0f, 0.55f))
            //.background(Color.Red)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("Tuesday")
                Text("02 January 2022")
                Text("06 43 AM")
                Text(text = "z")
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.16f)
                //.background(Color.Green)
                .align(BiasAlignment(0f, 1f))
        ) {
            Row(
                Modifier.fillMaxSize(),
                Arrangement.SpaceEvenly,
                Alignment.CenterVertically
            ) {
                Column(
                    Modifier.clickable {

                        println(time)
                    }
                ) {
                    if(state.isEmpty()) {
                       CircularProgressIndicator()
                    } else {
                        Text(WeatherDateFormat.getTime(state[0].hourly[0].dt, dateFormat = "hh:mm"))
                        Text(state[0].hourly[0].temp.roundToInt().toString())
                    }

                }
                Column() {
                    if(state.isEmpty()) {
                        CircularProgressIndicator()
                    } else {
                        Text(WeatherDateFormat.getTime(state[0].hourly[1].dt, dateFormat = "hh:mm"))
                        Text(state[0].hourly[1].temp.roundToInt().toString())
                    }
                }
                Column() {
                    if(state.isEmpty()) {
                        CircularProgressIndicator()
                    } else {
                        Text(WeatherDateFormat.getTime(state[0].hourly[2].dt, dateFormat = "hh:mm"))
                        Text(state[0].hourly[2].temp.roundToInt().toString())
                    }
                }
                Column() {
                    if(state.isEmpty()) {
                        CircularProgressIndicator()
                    } else {
                        Text(WeatherDateFormat.getTime(state[0].hourly[3].dt, dateFormat = "dd/MM/yyyy hh:mm"))
                        Text(state[0].hourly[3].temp.roundToInt().toString())
                    }
                }
                Column() {
                    if(state.isEmpty()) {
                        CircularProgressIndicator()
                    } else {
                        Text(WeatherDateFormat.getTime(state[0].hourly[4].dt, dateFormat = "hh:mm"))
                        Text(state[0].hourly[4].temp.roundToInt().toString())
                    }
                }
                Column() {
                    if(state.isEmpty()) {
                        CircularProgressIndicator()
                    } else {
                        Text(WeatherDateFormat.getTime(state[0].hourly[5].dt.toLong(), dateFormat = "hh:mm"))
                        Text(state[0].hourly[5].temp.roundToInt().toString())
                    }
                }
            }
        }

    }
}
