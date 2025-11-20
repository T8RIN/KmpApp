package com.example.kmpapp

sealed interface WeatherState {
    object Loading : WeatherState
    data class Error(val msg: String) : WeatherState
    data class Success(val data: WeatherResponse) : WeatherState
}