package com.example.weatherapp.api.openweather

import android.annotation.SuppressLint
import android.os.Build
import android.text.format.DateFormat
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun main(args: Array<String>) {
    println(getTime(1647910800, "HH:mm"))
    println(getTime(1648920800, "HH:mm"))
    println(getTime(1647920042000, "HH:mm"))
}

@SuppressLint("WeekBasedYear")
@RequiresApi(Build.VERSION_CODES.O)
fun getTime(milliSeconds: Long, dateFormat: String): String {

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    // JVM representation of a millisecond epoch absolute instant
    val instant = Instant.ofEpochMilli(milliSeconds)

    // Adding the timezone information to be able to format it (change accordingly)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(date).toString() // 10/12/2019 06:3
}

//object WeatherNetworkService {
//    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
//    private const val API_KEY = "76bb42ac7cb89fc255ba962c7916dd20"
//    private const val UNITS = "metric"
//
//    suspend fun getWeather(): Weather {
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(WeatherApiInterface::class.java)
//        return retrofitBuilder.getForecast(50.0, 36.25, units = UNITS, appid = API_KEY)
//    }
//}