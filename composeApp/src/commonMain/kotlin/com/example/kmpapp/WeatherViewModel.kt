package com.example.kmpapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel {

    private val pref = WeatherPreferences()
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _city: MutableStateFlow<String> = MutableStateFlow("")
    val city = _city.asStateFlow()

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state: StateFlow<WeatherState> = _state

    init {
        reload()

        viewModelScope.launch {
            pref.cityFlow.collectLatest { new ->
                _city.update { new }
            }
        }
    }

    fun onCityChange(v: String) {
        _city.update { v }
        viewModelScope.launch {
            pref.setCity(v)
        }
    }

    fun reload() {
        viewModelScope.launch {
            if (city.value.isBlank()) {
                _state.value = WeatherState.Error("Введите название города")
                return@launch
            }
            _state.value = WeatherState.Loading
            try {
                val data = loadWeather(city.value)
                _state.value = WeatherState.Success(data)
            } catch (e: Exception) {
                _state.value = WeatherState.Error("Ошибка загрузки погоды")
            }
        }
    }
}
