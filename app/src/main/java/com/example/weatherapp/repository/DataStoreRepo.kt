package com.example.weatherapp.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("city")

class DataStoreRepo @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val dataStore = context.dataStore

    suspend fun save(value: String, lat: Double, lon: Double, state: String, country: String) {
        dataStore.edit {
            it[stringPreferencesKey("city")] = value
            it[stringPreferencesKey("lat")] = lat.toString()
            it[stringPreferencesKey("lon")] = lon.toString()
            it[stringPreferencesKey("state")] = lon.toString()
            it[stringPreferencesKey("country")] = lon.toString()
        }
    }

    suspend fun readAll(): Map<Preferences.Key<*>, Any> {
        return dataStore.data.first().asMap()
    }
    suspend fun getCityName(): String? {
        return dataStore.data.first()[stringPreferencesKey("city")]
    }
    suspend fun getLat(): String? {
        return dataStore.data.first()[stringPreferencesKey("lat")]
    }

    suspend fun getLon(): String? {
        return dataStore.data.first()[stringPreferencesKey("lon")]
    }

    suspend fun count(): Int {
        return dataStore.data.first().asMap().count()
    }
}