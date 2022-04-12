package com.example.weatherapp.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.data.model.city.CityItem
import com.example.weatherapp.navigation.Screens
import com.example.weatherapp.repository.DataStoreRepo
import com.example.weatherapp.ui.main.MainViewModel
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NewFindCityScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    // var cityViewModel = hiltViewModel<FindCityViewModel>()
    val dataStore = DataStoreRepo(LocalContext.current)
    var cityName by remember { mutableStateOf("") }
    scope.launch {
        cityName = dataStore.getCityName() ?: ""
    }

    // val dataStore = DataStoreRepo(DataStoreModule.providePreferenceDataStore(LocalContext.current))
    var textFieldVal by remember { mutableStateOf("") }


    val homeViewModel = hiltViewModel<MainViewModel>()
    var arr by remember { mutableStateOf(emptyList<CityItem>()) }

    scope.launch {
        println(arr)
    }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF5E6174),
                        Color(0xFF1F2027)
                    )
                )
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp),
                    //.align(Alignment.Center),
                //.clip(shape = RoundedCornerShape(30.dp))
                //.border(2.dp, Color(0xFF9B9EAD), shape = RoundedCornerShape(30.dp)),
                value = textFieldVal,
                onValueChange = { textFieldVal = it.replace("\r", "").replace("\n", "") },
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                )
            )

            Button(onClick = {
                if (textFieldVal != "") {
                    scope.launch {
                        arr = homeViewModel.findCity(textFieldVal)
                    }
                }
            }) {
                Text("Find city")
            }
            Button(onClick = {
                scope.launch {
                    if (dataStore.count() == 0) {
                        println("Its empty")
                    } else {
                        println(dataStore.getCityName())
                        println(dataStore.count())
                    }
                }
            }) {
                Text(text = "Test dataStore")
            }

            CityItemComponent(
                city = cityName,
                onClick = { navController.navigate(Screens.MainScreen.route) })

            if (arr.isNotEmpty()) {
                arr.forEach {
                    CityItemComponent(
                        city = "${it.name} ${it.country ?: ""} ${it.state ?: ""}",
                        onClick = {
                            scope.launch {
                                // city.saveCityName(it.name)
                                dataStore.save(
                                    it.name,
                                    it.lat,
                                    it.lon,
                                    it.state ?: "",
                                    it.country ?: ""
                                )
                                navController.navigate(Screens.MainScreen.route)
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun CityItemComponent(
    city: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .border(2.dp, Color.Gray.copy(0.5f), shape = RoundedCornerShape(30.dp))
            .clickable {
                onClick()
            }
    ) {
        Text(city)
        Image(
            modifier = Modifier.align(Alignment.CenterEnd),
            imageVector = ImageVector.vectorResource(R.drawable.cloud_sun),
            contentDescription = null
        )
    }
}