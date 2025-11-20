package com.example.kmpapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Back(data: WeatherResponse) {
    Box(
        Modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(size = 28.dp),
                shadow = Shadow(
                    radius = 15.dp,
                    spread = 2.dp,
                    color = Color.Black.copy(0.5f)
                )
            )
            .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(28.dp))
            .padding(24.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Подробнее", fontSize = 28.sp, color = Color.White)
            Text("Страна: ${data.location.country}", color = Color.LightGray)
            Text("Ветер: ${data.current.wind_kph} км/ч", color = Color.LightGray)
            Text("Влажность: ${data.current.humidity}%", color = Color.LightGray)
        }
    }
}