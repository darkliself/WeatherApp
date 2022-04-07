package com.example.weatherapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.navigation.Screens


@Composable
fun TopBarNav(navController: NavController) {
    var cityName by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(.12f)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF484B5B),
                        Color(0xFF454857)
                    )
                )
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("x")
            FindCityTextField("")
            Text("x")
        }

        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                Modifier.fillMaxSize()// .background(color = Color.Cyan)
            ) {

                CategoryCard(
                    text = "Today",
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = {
                        navController.navigate(Screens.MainScreen.route)
                    }
                )

                CategoryCard(
                    text = "Forecast",
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        navController.navigate(Screens.ForecastScreen.route)
                    }
                )

                CategoryCard(
                    text = "Precipitation",
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        navController.navigate(Screens.PrecipitationScreen.route)
                    }
                )
            }
        }
    }
}


@Composable
fun CategoryCard(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.334f)
            .fillMaxHeight()
            .clickable {
                onClick()
            },
    ) {
        Text(text, modifier = Modifier.align(Alignment.Center))

        // indicator
        Divider(
            modifier = Modifier
                .fillMaxWidth(.3f)
                .align(Alignment.BottomCenter)
                .zIndex(1f),

            color = Color(0xFFD8D8D8),
            thickness = 2.dp,
        )
        // bottom border
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),

            color = Color(0xFF868794),
            thickness = 2.dp,
        )

    }
}

@Composable
fun FindCityTextField(
    value: String,

    ) {
    var angel by remember { mutableStateOf(0f) }
    val animate by animateFloatAsState(targetValue = angel)
    var city by remember { mutableStateOf(value) }
    TextField(city, onValueChange = { city = it }, trailingIcon = {

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow), null,
            modifier = Modifier
                .rotate(animate)
                .animateContentSize(
                    animationSpec = tween(
                        delayMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )
                .clickable {
                    angel += 360f
                }
        )
    })
}