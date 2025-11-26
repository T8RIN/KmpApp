package com.example.kmpapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeatherViewModel {

    private val pref = WeatherPreferences()
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    val city = pref.cityFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ""
    )

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state: StateFlow<WeatherState> = _state

    init {
        reload()
    }

    fun onCityChange(v: String) {
        viewModelScope.launch { pref.setCity(v) }
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
