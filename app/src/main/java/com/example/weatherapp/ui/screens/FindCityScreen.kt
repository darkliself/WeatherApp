package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.city.CityItem
import com.example.weatherapp.navigation.Screens
import com.example.weatherapp.ui.main.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FindCityScreen(navController: NavHostController) {
    var result by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    var text by remember {
        mutableStateOf("")
    }
    val homeViewModel = hiltViewModel<MainViewModel>()
    var arr by remember { mutableStateOf(emptyList<CityItem>()) }
    var cities: MutableList<String>
    scope.launch {
        // arr = homeViewModel.findCity("k")
        println(arr)
    }
    Surface(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (arr.isEmpty()) {
                CircularProgressIndicator()
            } else {
                cities = mutableListOf()
                arr.forEach {
                    // cities += " " + it.name
                    cities.add("${it.name} ${it.country} ${it.state}")
                }
                cities.distinct().forEach {
                    Text(it)
                }
                result = cities[0]

            }
            Row() {


                TextField(
                    placeholder = {
                        Text(
                            "this is placeholder",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    },
                    value = text,
                    label = { Text("Search") },
                    onValueChange = { text = it },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(8.dp)
                        .drawWithContent {
                            drawContent()
                            clipRect { // Not needed if you do not care about painting half stroke outside
                                drawLine(
                                    brush = SolidColor(Color.Red),
                                    strokeWidth = 5F * density,
                                    cap = StrokeCap.Square,
                                    start = Offset(x = 0f, y = size.height),
                                    end = Offset(x = size.width, y = size.height)
                                )
                            }

                        },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center
                    ),
                    singleLine = true,
                )
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = {
                        scope.launch {
                            arr = homeViewModel.findCity(text)
                            if (arr.isEmpty()) {
                                println("Not found cities")
                            } else {

                            }
                        }
                        // navController.navigate(Screens.Weather.route)
                    }) {
                    Text("Find city")
                }
            }
            Text(result)
        }
    }

}
