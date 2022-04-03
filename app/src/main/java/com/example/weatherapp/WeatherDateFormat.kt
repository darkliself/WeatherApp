package com.example.weatherapp

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_MONTH


fun main() {
    println(WeatherDateFormat.getDayOfTheMonth(1649235600))
}

object WeatherDateFormat {
    @SuppressLint("ConstantLocale")
    private val calendar = Calendar.getInstance(Locale.getDefault())

    fun getMouth(seconds: Long = Date().time / 1000, timeZone: Long = 0L): String? {
        calendar.time = Date((seconds - timeZone) * 1000)
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)
    }

    fun getDayOfTheWeek(seconds: Long = Date().time / 1000, timeZone: Long = 0L): String? {
        calendar.time = Date((seconds - timeZone) * 1000)
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)
    }

    fun getDayOfTheMonth(seconds: Long = Date().time / 1000, timeZone: Long = 0L): Int {
        calendar.time = Date((seconds - timeZone) * 1000)
        return calendar[DAY_OF_MONTH]
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(seconds: Long = Date().time / 1000, timeOffset: Int = 0,  dateFormat: String): String {
        val formatter: DateFormat = SimpleDateFormat(dateFormat)
        calendar.timeInMillis = (seconds + timeOffset) * 1000
        return formatter.format(calendar.time)
    }
}