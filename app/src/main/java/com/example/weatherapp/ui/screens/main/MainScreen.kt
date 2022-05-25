package com.example.weatherapp.ui.screens.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.WeatherDateFormat
import com.example.weatherapp.data.model.FullWeather
import com.example.weatherapp.navigation.Screens
import com.example.weatherapp.ui.screens.search.ImageCard
import kotlin.math.roundToInt
import kotlin.properties.Delegates


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(navController: NavController) {
    lateinit var current: FullWeather.Current
    lateinit var daily: List<FullWeather.Daily>
    lateinit var hourly: List<FullWeather.Hourly>
    var timeOffset by Delegates.notNull<Int>()
    val homeViewModel = hiltViewModel<MainViewModel>()
    val fullWeather by homeViewModel.state.collectAsState()
    val cityInfo by homeViewModel.info.collectAsState()
    val dayOfTheWeek = WeatherDateFormat.getWeekDay()
    val day = WeatherDateFormat.getDayOfTheMonth()
    val month = WeatherDateFormat.getMonth()

    if (fullWeather.isNotEmpty()) {
        current = fullWeather[0].current
        daily = fullWeather[0].daily
        hourly = fullWeather[0].hourly
        timeOffset = fullWeather[0].timezoneOffset
    }

    if (fullWeather.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        LazyColumn(
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    //.zIndex(1f),
                ) {
                    Text(
                        text = "${cityInfo.first()}, ${cityInfo.drop(1).joinToString(" ")}",
                        modifier = Modifier.align(Alignment.Center),
                    )

                    Divider(
                        Modifier
                            .fillMaxWidth(0.6f)
                            .align(Alignment.BottomCenter),
                        color = Color.Black
                    )

                    ImageCard(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        img = R.drawable.search,
                        onClick = {
                            navController.navigate(Screens.NewFindCityScreen.route)
                        }
                    )
                }

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

                Column {
                    Row(
                        Modifier
                            .fillMaxHeight(.3f)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth(.3f)
                                .fillMaxHeight(),
                            imageVector = ImageVector.vectorResource(current.getIcon()),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                        )

                        Column {
                            Text(
                                "${current.temp.roundToInt()}°C",
                                fontSize = 90.sp,
                                color = Color(0xFFA2A4B5)
                            )

                            Text(
                                text = current.weather[0].description.replaceFirstChar { it.titlecase() },
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth(0.95f)
                            .height(40.dp),
                    ) {
                        WeatherInfoComponent(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .fillMaxWidth(0.6f),
                            // .padding(start = 10.dp),
                            items = listOf(
                                "${daily[0].temp.max.roundToInt()}°/${daily[0].temp.min.roundToInt()}° Feels like ",
                                "${current.feelsLike.roundToInt()}°C"
                            )
                        )

                        WeatherInfoComponent(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterEnd)
                                .fillMaxWidth(0.45f),
                            items = listOf("Wind ", "${current.windSpeed}m/s "),
                        )

                        Image(
                            modifier = Modifier
                                .rotate(current.windDeg.toFloat())
                                .align(Alignment.CenterEnd)
                                .padding(start = 10.dp, end = 5.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }

                    Image(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.divider),
                        contentDescription = null
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(70.dp)
                        .padding(top = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight()
                    ) {
                        WeatherInfoComponent(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 20.dp),
                            items = listOf(
                                "Sunrise: ",
                                WeatherDateFormat.getTime(
                                    seconds = current.sunrise,
                                    timeOffset = timeOffset,
                                    dateFormat = "hh:mm"
                                )
                            )
                        )

                        WeatherInfoComponent(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 20.dp),
                            items = listOf("Humidity: ", "${daily[0].humidity}%")
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth(0.45f)
                            .fillMaxHeight()
                            .align(Alignment.TopEnd)
                    ) {
                        WeatherInfoComponent(
                            modifier = Modifier.align(Alignment.TopStart),
                            items = listOf("Wind: ", "${current.windSpeed} m/s")
                        )

                        WeatherInfoComponent(
                            modifier = Modifier.align(Alignment.BottomStart),
                            items = listOf(
                                "Sunset: ",
                                WeatherDateFormat.getTime(
                                    seconds = current.sunset,
                                    timeOffset = timeOffset,
                                    dateFormat = "hh:mm"
                                )
                            )
                        )
                    }
                }

                LazyRow(
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(hourly.count() - 1) { index ->
                        WeatherHourlyItem(
                            time = WeatherDateFormat.getTime(
                                seconds = hourly[index + 1].dt,
                                timeOffset = timeOffset,
                                dateFormat = "hh:mm"
                            ),
                            icon = hourly[index + 1].getIcon(),
                            temp = "${hourly[index + 1].temp.roundToInt()}"
                        )
                    }
                }
            }

            item {
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
                            DailyComponent(
                                weekDay = WeatherDateFormat.getWeekDay(daily[it + 1].dt)
                                    .toString(),
                                icon = daily[it + 1].getIcon(),
                                max = "${daily[it + 1].temp.max.roundToInt()}°",
                                min = "${daily[it + 1].temp.min.roundToInt()}°",
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun WeatherHourlyItem(
    time: String,
    icon: Int,
    temp: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(70.dp)
            .height(140.dp)
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(Color(0x1A000000))
        //.border(1.dp, Color.White, shape = RoundedCornerShape(30.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(time)
            Image(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Text("$temp°C")
        }
    }
}

@Composable
private fun DailyComponent(
    weekDay: String,
    icon: Int,
    max: String,
    min: String
) {
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
            Text(weekDay, Modifier.align(Alignment.CenterStart))
            Image(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxWidth(0.2f)
                    .fillMaxHeight(.2f),
                contentScale = ContentScale.Fit
            )
        }
        Box(
            Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 5.dp, end = 35.dp)
        ) {
            Text(max, Modifier.align(Alignment.CenterStart))
            Text(min, Modifier.align(Alignment.CenterEnd))
        }
    }
}

@Composable
private fun WeatherInfoComponent(
    modifier: Modifier = Modifier,
    items: List<String>,
) {
    Row(modifier = modifier) {
        items.forEachIndexed { index, s ->
            val textColor = if (index % 2 == 0) Color(0xFF9B9EAD) else Color.White
            Text(text = s, color = textColor)
        }
    }
}


private fun FullWeather.Daily.getIcon(): Int {
    return getIconId(weather.first().icon)
}

private fun FullWeather.Current.getIcon(): Int {
    return getIconId(weather.first().icon)
}

private fun FullWeather.Hourly.getIcon(): Int {
    return getIconId(weather.first().icon)
}

private fun getIconId(str: String): Int {
    return when (str) {
        "01d" -> R.drawable.ic_01d
        "01n" -> R.drawable.ic_01n
        "02d" -> R.drawable.ic_02d
        "02n" -> R.drawable.ic_02n
        "03d" -> R.drawable.ic_03d
        "03n" -> R.drawable.ic_03n
        "04d" -> R.drawable.ic_04d
        "04n" -> R.drawable.ic_04n
        "09d" -> R.drawable.ic_09d
        "09n" -> R.drawable.ic_09n
        "10d" -> R.drawable.ic_10d
        "10n" -> R.drawable.ic_10n
        "11d" -> R.drawable.ic_11d
        "11n" -> R.drawable.ic_11n
        else -> R.drawable.cloud_sun
    }
}