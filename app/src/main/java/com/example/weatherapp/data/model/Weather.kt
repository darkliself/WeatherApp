package com.example.weatherapp.data.model

data class Weather(
        val current: Current,
        val daily: List<Daily>,
        val hourly: List<Hourly>,
        val lat: Int,
        val lon: Double,
        val timezone: String,
        val timezone_offset: Long
    )

data class TimeZoneData(
    val timezone: String,
    val timezone_offset: Long
) {

}

data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Long,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
)

data class Daily(
    val clouds: Int,
    val dt: Long,
    val feels_like: FeelsLike,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val snow: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
) {
    data class FeelsLike(
        val day: Double,
        val eve: Double,
        val morn: Double,
        val night: Double
    )
    data class Temp(
        val day: Double,
        val eve: Double,
        val max: Double,
        val min: Double,
        val morn: Double,
        val night: Double
    )
}

data class Hourly(
    val clouds: String,
    val dt: Long,
    val feels_like: String,
    val humidity: String,
    val pop: String,
    val pressure: String,
    val temp: String,
    val weather: List<Weather>,
    val wind_deg: String,
    val wind_gust: String,
    val wind_speed: String
)
