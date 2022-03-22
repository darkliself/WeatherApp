package com.example.weatherapp.di

import com.example.weatherapp.api.openweather.WeatherApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): WeatherApiInterface {
        return builder.build().create(WeatherApiInterface::class.java)

    }
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
}


object Constants {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY = "76bb42ac7cb89fc255ba962c7916dd20"
    const val UNITS = "metric"
}