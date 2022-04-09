package com.example.weatherapp.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapp.api.openweather.WeatherApiInterface
import com.example.weatherapp.di.DataStoreModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


class DataStoreRepo @Inject constructor(
    private val dataStoreModule: DataStore<Preferences>,
    // private val context: Context
) {
//    @Singleton
//    private val dataStore = dataStoreModule.data.providePreferenceDataStore(context)


    suspend fun save(key: String, value: String) {
        dataStoreModule.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun delete(key: String) {
        dataStoreModule.edit {
            it.remove(stringPreferencesKey(key))
        }
    }

    suspend fun deleteAll() {
        dataStoreModule.edit {
            it.clear()
        }
    }

    suspend fun readAll(): Map<Preferences.Key<*>, Any> {
        return dataStoreModule.data.first().asMap()
    }

    suspend fun count(): Int {
        return dataStoreModule.data.first().asMap().count()
    }
}


//@Singleton
//class DataStoreRepo(context: Context) {
//    private val _context = context
//    // private val Context.dataStore by preferencesDataStore(name = "nickname")
//    private val Context.dataStore by preferencesDataStore(name = "nickname")
//
////    companion object SingletonRepo {
////
////    }
//
//    suspend fun save(key: String, value: String) {
//        _context.dataStore.edit {
//            it[stringPreferencesKey(key)] = value
//        }
//    }
//
//    suspend fun delete(key: String) {
//        _context.dataStore.edit {
//            it.remove(stringPreferencesKey(key))
//        }
//    }
//
//    suspend fun deleteAll() {
//        _context.dataStore.edit {
//            it.clear()
//        }
//    }
//
//    suspend fun readAll(): Map<Preferences.Key<*>, Any> {
//        return _context.dataStore.data.first().asMap()
//    }
//
//    suspend fun count(): Int {
//        return _context.dataStore.data.first().asMap().count()
//    }
//
//}