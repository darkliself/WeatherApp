package com.example.weatherapp.ui.search

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
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
import kotlinx.coroutines.*


@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NewFindCityScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val dataStore = DataStoreRepo(LocalContext.current)
    var cityName by remember { mutableStateOf(emptyList<String>()) }
    var visible by remember { mutableStateOf(false) }
    scope.launch {
        if (dataStore.count() > 0) {
            cityName = dataStore.getCityInfo()
        }
    }

    var textFieldVal by remember { mutableStateOf("") }
    val homeViewModel = hiltViewModel<MainViewModel>()
    var arr by remember { mutableStateOf(emptyList<CityItem>()) }

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
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.4f)
                .align(BiasAlignment(horizontalBias = 0f, verticalBias = 0.8f)),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Image(
                    ImageVector.vectorResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 10.dp)
                        .clickable {

                        },
                    contentScale = ContentScale.Fit
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp),
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
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        if (textFieldVal != "") {
                            visible = false
                            scope.launch {
                                keyboardController?.hide()
                                delay(1000)
                                arr = emptyList()
                                arr = homeViewModel.findCity(textFieldVal)
                            }
                        }
                    }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                )
                Image(
                    ImageVector.vectorResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(start = 10.dp)
                        .clickable {
                            visible = false
                            if (textFieldVal != "") {
                                scope.launch {
                                    keyboardController?.hide()
                                    delay(1000)
                                    arr = emptyList()
                                    arr = homeViewModel.findCity(textFieldVal)

                                }
                            }
                        },
                    contentScale = ContentScale.Fit
                )
            }

            if (cityName.isNotEmpty()) {
                Text("Current place", modifier = Modifier.padding(bottom = 10.dp))
                var currentVisable by remember { mutableStateOf(false) }

                scope.launch(Dispatchers.IO) {
                    delay(500)
                    currentVisable = true
                }
                AnimatedVisibility(
                    visible = currentVisable,
                    enter = fadeIn() + expandHorizontally(),
                    exit = fadeOut() + shrinkHorizontally()
                ) {
                    CityItemComponent(
                        city = "${cityName.first()}, ${cityName.drop(1).take(2).joinToString(" ")}",
                        modifier = Modifier.padding(bottom = 20.dp),
                        onClick = { navController.navigate(Screens.MainScreen.route) }
                    )
                }
            }

            if (arr.isNotEmpty()) {
                Text("Search result", modifier = Modifier.padding(bottom = 10.dp))
                LazyColumn(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    scope.launch() {
                        delay(500)
                        visible = true
                    }
                    arr.forEach {
                        item() {
                            AnimatedVisibility(
                                visible = visible,
                                enter = fadeIn() + expandHorizontally(),
                                exit = fadeOut() + shrinkHorizontally()
                            ) {
                                CityItemComponent(
                                    city = "${it.name}, ${it.country ?: ""} ${it.state ?: ""}",
                                    modifier = Modifier.padding(bottom = 20.dp),
                                    onClick = {
                                        scope.launch {
                                            dataStore.save(
                                                name = it.name,
                                                lat = it.lat,
                                                lon = it.lon,
                                                state = it.state ?: "",
                                                country = it.country ?: ""
                                            )
                                            // navController.navigate(Screens.MainScreen.route)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CityItemComponent(
    city: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

//    AnimatedVisibility(
//        visible = true,
//        enter = fadeIn() + expandHorizontally(),
//        exit = fadeOut() + shrinkHorizontally()
//    ) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .border(2.dp, Color.Gray.copy(0.5f), shape = RoundedCornerShape(30.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF6972AF),
                        Color(0xFF171C47).copy(0.8f)
                    )
                )
            )
            .clickable {
                onClick()
            },
    ) {
        Text(
            text = city,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
//    }
}