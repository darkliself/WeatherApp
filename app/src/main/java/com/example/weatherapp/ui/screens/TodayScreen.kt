package com.example.weatherapp.ui.screens

import android.graphics.drawable.VectorDrawable
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R


@Composable
fun TodayScreen() {
    val listzz = listOf(
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123",
        "123"
    )
    LazyColumn(
        Modifier
            .fillMaxWidth()
            //.fillMaxHeight(2f)
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
        item() {
            Column() {
                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(shape = RoundedCornerShape(50f))
                        .background(Color(0xFF32333E))

                ) {
                    Text(
                        text = "Saturday, 11 Sept",
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
                    Column() {
                        Text("33C", fontSize = 70.sp)
                        Text("Partly cloud", fontSize = 15.sp)
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
                    Text("29°/27° Feels like 39°C")
                    Text("Wind 9 KM/H WSW")
                }
                // Divider(Modifier.fillMaxWidth(.8f).padding(5.dp).align(CenterHorizontally), Color.Black, 1.dp)
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
                Text("Precipitation: 21%")
                Text("Humidity: 59%")
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("Wind: 10 km/h")
                Text("Sunset: 29%")
            }
            LazyRow(
                contentPadding = PaddingValues(5.dp)
            ) {
                items(listzz.count()) { index ->
                    WeatherWidgetItem(item = listzz[index], Modifier)
                }
            }

        }

        item() {
            Box(
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(400.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF2F313A),
                                Color(0xFF232329)
                            )
                        )
                    )
            ) {
                repeat(20) { Text("abracadabra") }
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

