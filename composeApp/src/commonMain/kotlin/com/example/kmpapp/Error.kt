package com.example.kmpapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Error(
    msg: String,
    retry: () -> Unit,
    onCityChange: (String) -> Unit,
    city: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(msg, color = Color.White)
        Spacer(Modifier.height(16.dp))
        Button(onClick = retry) { Text("Повторить") }

        Spacer(Modifier.weight(1f))
        Row {
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
                modifier = Modifier.weight(1f)
            )
            FloatingActionButton(
                onClick = { onCityChange(city) },
                content = {
                    Text("\uD83D\uDD0D\uFE0F")
                }
            )
        }
    }
}