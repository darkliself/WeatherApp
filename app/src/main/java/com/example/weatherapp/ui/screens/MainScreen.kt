package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.model.Current
import com.example.weatherapp.ui.theme.main.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen() {
    val scope = rememberCoroutineScope()

    //val homeViewModel = viewModel(modelClass = MainViewModel::class.java)
    val homeViewModel = hiltViewModel<MainViewModel>()
    val state by homeViewModel.state.collectAsState()
    //var state2 by remember { mutableStateOf(emptyList<Current>())}

    scope.launch {
        // state2 = listOf(homeViewModel.hourly())
    }

//    Column() {
//        if (state2.isEmpty()) {
//           CircularProgressIndicator(
//               Modifier.fillMaxSize()
//           )
//        } else {
//            Text(text = state2.toString())
//        }
//
//    }
}
