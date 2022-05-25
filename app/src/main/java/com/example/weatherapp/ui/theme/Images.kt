package com.example.weatherapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R

object Images {

    @Composable
    fun getImage(): Painter {
        val image: Painter = painterResource(id = R.drawable.img)
        return image
    }
}