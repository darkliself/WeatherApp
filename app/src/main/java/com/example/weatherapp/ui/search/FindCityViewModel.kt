package com.example.weatherapp.ui.search


//
//import androidx.datastore.preferences.core.Preferences
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.weatherapp.data.model.*
//import com.example.weatherapp.data.model.city.CityItem
//import com.example.weatherapp.repository.DataStoreRepo
//import com.example.weatherapp.repository.WeatherRepo
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class FindCityViewModel @Inject constructor(
//    private val cityRepository: DataStoreRepo
//) : ViewModel() {
//
//    private val _state = MutableStateFlow(emptyList<String>())
//    val state: MutableStateFlow<List<String>>
//        get() = _state
//
//    init {
//        viewModelScope.launch {
//            val city = cityRepository.count().toString()
//            _state.value = listOf(city)
//        }
//    }
//
//    suspend fun getCity(): String {
//        return cityRepository.readAll().toString()
//    }
//}