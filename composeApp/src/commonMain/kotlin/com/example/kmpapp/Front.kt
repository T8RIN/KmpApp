package com.example.kmpapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage

@Composable
fun Front(
    data: WeatherResponse,
    city: String,
    onCityChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
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
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedTextField(
                value = city,
                onValueChange = onCityChange,
                label = { Text("Город") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.LightGray,
                    unfocusedLabelColor = Color.LightGray,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubcomposeAsyncImage(
                    model = "https:${data.current.condition.icon}",
                    loading = {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    },
                    contentDescription = null,
                    modifier = Modifier.size(96.dp)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "нажми, чтобы перевернуть",
                    color = Color.White.copy(0.5f),
                    fontSize = 12.sp
                )
            }

            Row {
                Text(
                    text = "${data.current.temp_c}°C",
                    fontSize = 48.sp,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                FloatingActionButton(
                    onClick = { onCityChange(city) },
                    content = { Text("Обновить", modifier = Modifier.padding(horizontal = 16.dp)) }
                )
            }
            Text(data.current.condition.text, fontSize = 20.sp, color = Color.LightGray)
        }
    }
}
