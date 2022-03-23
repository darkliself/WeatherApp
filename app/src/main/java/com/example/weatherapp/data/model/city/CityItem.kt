package com.example.weatherapp.data.model.city



data class CityItem(
    val country: String,
    val lat: Double,
//    @Json(name = "local_names")
//    val localNames: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)