package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Current
import com.example.weatherapp.data.model.Daily
import com.example.weatherapp.ui.main.MainViewModel
import java.time.Clock
import java.time.LocalDate
import java.util.*
import kotlin.math.roundToInt


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TodayScreen() {
    val scope = rememberCoroutineScope()
    val homeViewModel = hiltViewModel<MainViewModel>()
    val testList = listOf("123", "123", "123", "123", "123", "123", "123")

    val fullWeather by homeViewModel.state.collectAsState()
    lateinit var current: Current
    lateinit var daily: List<Daily>
    val date = LocalDate.now(Clock.systemDefaultZone())
    val dayOfTheWeek =
        date.dayOfWeek.name.lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }
    val day = date.dayOfMonth
    val month = date.month.toString().lowercase().replaceFirstChar { it.uppercase() }

    if (fullWeather.isNotEmpty()) {
        current = fullWeather[0].current
        daily = fullWeather[0].daily
    }

    // println(arr)

    LazyColumn(
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
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (fullWeather.isEmpty()) {
            item {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }
        } else {


            item() {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .padding(20.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(shape = RoundedCornerShape(50f))
                            .background(Color(0xFF32333E))

                    ) {

                        Text(
                            text = "$dayOfTheWeek, $day $month",
                            modifier = Modifier.padding(10.dp),
                            fontSize = 20.sp,
                            color = Color(0xFF9B9EAD)
                        )
                    }
                }


                Column() {
                    Row(
                        Modifier
                            .fillMaxHeight(.3f)
                            .fillMaxWidth(),
                        // .border(2.dp, Color.Black),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            modifier = Modifier.wrapContentSize(),
                            imageVector = ImageVector.vectorResource(R.drawable.cloud_sun),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                        Column(
                            Modifier.clickable {
                                Log.d("viewModelResult", fullWeather.toString())
                            }
                        ) {
                            Text(
                                "${current.temp.roundToInt()}°C",
                                fontSize = 70.sp,
                                color = Color(0xFFA2A4B5)
                            )
                            Text(
                                current.weather[0].description.replaceFirstChar {
                                    it.titlecase()
                                },
                                fontSize = 15.sp
                            )
                        }
                    }
                }

                Column() {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherInfoWidget(listOf(
                            "${daily[0].temp.max.roundToInt()}°/${daily[0].temp.min.roundToInt()}° Feels like ",
                            "${current.feels_like.roundToInt()}°C"))
                        WeatherInfoWidget(listOf("Wind ", "${current.wind_speed}m/s ", "WSW"))
                        Text("->", modifier = Modifier.rotate(170f))
                    }
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .align(Alignment.CenterHorizontally),
                        imageVector = ImageVector.vectorResource(id = R.drawable.divider),
                        contentDescription = null
                    )
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherInfoWidget(listOf("Precipitation: ", "21%"))
                    WeatherInfoWidget(listOf("Humidity: ", "59%"))
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherInfoWidget(listOf("Wind: ", "10 km/h"))
                    WeatherInfoWidget(listOf("Sunset: ", "29%"))
                }
                LazyRow(
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(testList.count()) { index ->
                        WeatherWidgetItem(item = testList[index], Modifier)
                    }
                }
            }

            item(20) {
                Box(
                    Modifier
                        .fillMaxWidth(0.9f)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF2F313A),
                                    Color(0xFF232329)
                                )
                            )
                        )
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        repeat(20) {
                            DailyItem(listOf("today", "@", "67°", "67°"))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun WeatherWidgetItem(
    item: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(70.dp)
            .height(140.dp)
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(Color(0xFFFFFFFF).copy(.2f))
            .border(2.dp, Color.White, shape = RoundedCornerShape(30.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(item)
            Text(item)
            Text(item)
        }
    }
}

data class WeatherItem(
    val time: String,
    val temp: Int,
    val icon: String,
)

@Composable
fun DailyItem(items: List<String>) {
    Row(
        Modifier
            .padding(bottom = 5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEach {
            Text(it, color = Color.White)
        }
    }
}

@Composable
fun WeatherInfoWidget(items: List<String>) {
    Row() {
        items.forEachIndexed { index, s ->
            val textColor = if (index % 2 == 0) Color(0xFF9B9EAD) else Color.White
            Text(text = s, color = textColor)
        }
    }
}


