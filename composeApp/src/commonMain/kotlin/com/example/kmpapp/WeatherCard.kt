package com.example.kmpapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun WeatherCard(
    data: WeatherResponse,
    city: String,
    onCityChange: (String) -> Unit
) {
    var flipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(800)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 16f * density
            }
            .clickable { flipped = !flipped }
    ) {
        if (rotation <= 90f) {
            Front(data, city, onCityChange)
        } else {
            Box(Modifier.graphicsLayer { rotationY = 180f }) {
                Back(data)
            }
        }
    }
}
