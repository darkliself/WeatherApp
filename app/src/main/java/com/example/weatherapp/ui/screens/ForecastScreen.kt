package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ForecastScreen() {
    Column(
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
            )
    ) {
        LazyColumn(
            Modifier.fillMaxSize()
        ) {
            item() {

            }
        }
    }
}
