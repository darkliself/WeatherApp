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
import androidx.compose.ui.draw.alpha
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
import com.example.weatherapp.WeatherDateFormat
import com.example.weatherapp.data.model.Current
import com.example.weatherapp.data.model.Daily
import com.example.weatherapp.data.model.Hourly
import com.example.weatherapp.ui.main.MainViewModel
import kotlin.math.roundToInt
import kotlin.properties.Delegates


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TodayScreen() {
    lateinit var current: Current
    lateinit var daily: List<Daily>
    lateinit var hourly: List<Hourly>
    var timeOffset by Delegates.notNull<Int>()
    val scope = rememberCoroutineScope()
    val homeViewModel = hiltViewModel<MainViewModel>()
    val fullWeather by homeViewModel.state.collectAsState()
    val dayOfTheWeek = WeatherDateFormat.getDayOfTheWeek()
    val day = WeatherDateFormat.getDayOfTheMonth()
    val month = WeatherDateFormat.getMouth()

    if (fullWeather.isNotEmpty()) {
        current = fullWeather[0].current
        daily = fullWeather[0].daily
        hourly = fullWeather[0].hourly
        timeOffset = fullWeather[0].timezoneOffset
    }

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
                            .border(2.dp, Color.Red),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherInfoWidget(
                            listOf(
                                "${daily[0].temp.max.roundToInt()}°/${daily[0].temp.min.roundToInt()}° Feels like ",
                                "${current.feelsLike.roundToInt()}°C"
                            )
                        )
                        WeatherInfoWidget(listOf("Wind ", "${current.windSpeed}m/s ", "WSW"))
                    }
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .align(Alignment.CenterHorizontally),
                        imageVector = ImageVector.vectorResource(id = R.drawable.divider),
                        contentDescription = null
                    )
                }

                Box(
                    Modifier
                        .fillMaxWidth(0.9f)
                        .height(70.dp)
                        .padding(top = 20.dp)
                        .border(2.dp, Color.Black),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight()
                    ) {
                        WeatherInfoWidget(
                            listOf(
                                "Sunrise: ",
                                WeatherDateFormat.getTime(
                                    seconds = current.sunrise,
                                    timeOffset = timeOffset,
                                    dateFormat = "hh:mm"
                                )
                            ),
                            Modifier.align(Alignment.TopStart)
                        )
                        WeatherInfoWidget(
                            listOf("Humidity: ", "${daily[0].humidity}%"),
                            Modifier.align(Alignment.BottomStart)
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth(0.4f)
                            .fillMaxHeight()
                            .align(Alignment.TopEnd)
                    ) {
                        WeatherInfoWidget(listOf("Wind: ", "${current.windSpeed} m/s"), Modifier.align(Alignment.TopStart))
                        WeatherInfoWidget(
                            listOf(
                                "Sunset: ",
                                WeatherDateFormat.getTime(
                                    seconds = current.sunset,
                                    timeOffset = timeOffset,
                                    dateFormat = "hh:mm"
                                )
                            ),
                            Modifier.align(Alignment.BottomStart)
                        )
                    }
                }

//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .height(40.dp)
//                        .padding(10.dp),
//                    horizontalArrangement = Arrangement.SpaceAround
//                ) {
//                    WeatherInfoWidget(listOf("Wind: ", "${current.windSpeed} m/s"))
//                    WeatherInfoWidget(
//                        listOf(
//                            "Sunset: ",
//                            WeatherDateFormat.getTime(
//                                seconds = current.sunset,
//                                timeOffset = timeOffset,
//                                dateFormat = "hh:mm"
//                            )
//                        )
//                    )
//                }

                LazyRow(
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(hourly.count() - 1) { index ->
                        WeatherHourlyItem(
                            listOf(
                                WeatherDateFormat.getTime(
                                    seconds = hourly[index + 1].dt,
                                    timeOffset = timeOffset,
                                    dateFormat = "hh:mm"
                                ),
                                hourly[index + 1].weather[0].icon,
                                "${hourly[index + 1].temp.roundToInt()}°C"
                            )
                        )
                    }
                }
            }

            item() {
                Box(
                    Modifier
                        .fillMaxWidth(0.98f)
                        .padding(top = 30.dp)
                        .clip(shape = RoundedCornerShape(15.dp))

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
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(
                                Modifier
                                    .fillMaxWidth(0.3f)
                                    .padding(top = 30.dp, end = 30.dp, bottom = 20.dp)
                            ) {
                                Text("High", Modifier.align(Alignment.CenterStart))
                                Text("|", Modifier.align(Alignment.Center))
                                Text("Low", Modifier.align(Alignment.CenterEnd))
                            }
                        }
                        repeat(daily.size - 1) {
                            DailyWidget(
                                DailyItem(
                                    WeatherDateFormat.getDayOfTheWeek(daily[it + 1].dt).toString(),
                                    daily[it + 1].weather[0].icon,
                                    "${daily[it + 1].temp.max.roundToInt()}°",
                                    "${daily[it + 1].temp.min.roundToInt()}°",
                                )

                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun WeatherHourlyItem(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(70.dp)
            .height(140.dp)
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(Color(0xFFFFFFFF).copy(.2f))
            .border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEach { item ->
                Text(item)
            }

        }
    }
}

data class DailyItem(
    val day: String,
    val icon: String,
    val max: String,
    val min: String,
)

@Composable
fun DailyWidget(dailyItem: DailyItem) {
    Row(
        Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            Modifier
                .fillMaxWidth(0.4f)
                .padding(start = 20.dp)
        ) {
            Text(dailyItem.day, Modifier.align(Alignment.CenterStart))
            Text(dailyItem.icon, Modifier.align(Alignment.CenterEnd))
        }
        Box(
            Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 5.dp, end = 35.dp)
        ) {
            Text(dailyItem.max, Modifier.align(Alignment.CenterStart))
            Text(dailyItem.min, Modifier.align(Alignment.CenterEnd))
        }
    }
}

@Composable
fun WeatherInfoWidget(items: List<String>, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        items.forEachIndexed { index, s ->
            val textColor = if (index % 2 == 0) Color(0xFF9B9EAD) else Color.White
            Text(text = s, color = textColor)
        }
    }
}


