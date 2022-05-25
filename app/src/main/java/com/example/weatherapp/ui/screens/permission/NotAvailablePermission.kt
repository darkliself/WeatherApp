package com.example.weatherapp.ui.screens.permission

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R


@Composable
fun NotAvailablePermission() {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF5E6174),
                        Color(0xFF1F2027)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(

        ) {
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_geo_sticker), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("WE need your permission")
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            Modifier
                .size(60.dp)
                .background(Color.Red)
        ) {

        }

    }
}