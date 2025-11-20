package com.example.kmpapp

import kotlinx.coroutines.flow.MutableStateFlow

class WeatherPreferences {
    val cityFlow = MutableStateFlow("Казань")

    suspend fun setCity(city: String) = cityFlow.emit(city)
}
