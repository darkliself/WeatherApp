package com.example.weatherapp.data.model.city

import com.google.gson.annotations.SerializedName

data class LocalNames(
    @SerializedName("uk")
    val uk: String,
    @SerializedName("ar")
    val ar: String,
    @SerializedName("en")
    val en: String,
    @SerializedName("fr")
    val fr: String,
    @SerializedName("ko")
    val ko: String,
    @SerializedName("ru")
    val ru: String
)