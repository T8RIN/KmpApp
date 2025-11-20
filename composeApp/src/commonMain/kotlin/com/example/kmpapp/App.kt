package com.example.kmpapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF0F2027),
                            Color(0xFF203A43),
                            Color(0xFF2C5364)
                        )
                    )
                )
                .padding(24.dp)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val viewModel = remember { WeatherViewModel() }
            val state = viewModel.state.collectAsState().value
            val city by viewModel.city.collectAsState()

            when (state) {
                WeatherState.Loading -> CircularProgressIndicator(color = Color.White)
                is WeatherState.Error -> Error(
                    msg = state.msg,
                    retry = { viewModel.onCityChange(city) },
                    city = city,
                    onCityChange = viewModel::onCityChange
                )

                is WeatherState.Success -> WeatherCard(
                    data = state.data,
                    city = city,
                    onCityChange = viewModel::onCityChange
                )
            }
        }
    }
}