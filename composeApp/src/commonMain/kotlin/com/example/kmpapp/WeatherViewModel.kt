package com.example.kmpapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    private var job: Job? = null

    init {
        viewModelScope.launch { reload() }
    }

    fun onCityChange(v: String) {
        viewModelScope.launch { pref.setCity(v) }
        job?.cancel()
        job = viewModelScope.launch {
            delay(500)
            reload()
        }
    }

    suspend fun reload() {
        _state.value = WeatherState.Loading
        try {
            val data = loadWeather(city.value)
            _state.value = WeatherState.Success(data)
        } catch (e: Exception) {
            _state.value = WeatherState.Error(e.message ?: "Unknown error")
        }
    }
}
