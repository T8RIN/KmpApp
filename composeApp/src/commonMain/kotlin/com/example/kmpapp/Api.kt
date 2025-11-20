package com.example.kmpapp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val API_KEY = "92b64a6d838c43918e6181715240511"

private val http = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

@Serializable
data class WeatherResponse(
    val location: Location,
    val current: Current
)

@Serializable
data class Location(val name: String, val country: String)

@Serializable
data class Current(
    val temp_c: Double,
    val humidity: Int,
    val wind_kph: Double,
    val condition: Condition
)

@Serializable
data class Condition(val text: String, val icon: String)

suspend fun loadWeather(city: String): WeatherResponse =
    http.get("https://api.weatherapi.com/v1/current.json") {
        url {
            parameters.append("key", API_KEY)
            parameters.append("q", city)
            parameters.append("aqi", "no")
            parameters.append("lang", "ru")
        }
    }.body()
