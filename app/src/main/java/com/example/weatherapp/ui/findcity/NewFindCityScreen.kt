package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.data.model.city.CityItem
import com.example.weatherapp.di.DataStoreModule
import com.example.weatherapp.repository.DataStoreRepo
import com.example.weatherapp.ui.findcity.FindCityViewModel
import com.example.weatherapp.ui.main.MainViewModel
import kotlinx.coroutines.launch
import java.util.prefs.Preferences


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NewFindCityScreen() {
    var cityViewModel = hiltViewModel<FindCityViewModel>()
    val city = cityViewModel.state.collectAsState()
    // val dataStore = DataStoreRepo(DataStoreModule.providePreferenceDataStore(LocalContext.current))
    var textFieldVal by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    val homeViewModel = hiltViewModel<MainViewModel>()
    var arr by remember { mutableStateOf(emptyList<CityItem>()) }

    scope.launch {
        println(arr)
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.6f))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = textFieldVal, onValueChange = { textFieldVal = it })
            Button(onClick = {
                scope.launch {
                    arr = homeViewModel.findCity(textFieldVal)
                }

            }) {
                Text("Find city")
            }
            Button(onClick = {


                val count = city.value[0]
                if (count == "") {
                    println("Data store is empty")
                } else {
                    println(count)
                    Log.d("dataStore log", count.toString())
                }

            }) {
                Text(text = "Test dataStore")
            }
            if (arr.isNotEmpty()) {
                arr.forEach {
                    CityItemComponent("${it.name} ${it.country} ${it.state}")
                }
            }
        }
    }
}


@Composable
fun CityItemComponent(city: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .border(2.dp, Color.Gray.copy(0.5f), shape = RoundedCornerShape(30.dp))
    ) {
        Text(city)
        Image(
            modifier = Modifier.align(Alignment.CenterEnd),
            imageVector = ImageVector.vectorResource(R.drawable.cloud_sun),
            contentDescription = null
        )
    }
}