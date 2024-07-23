package com.a1573595.dailypulse.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a1573595.dailypulse.Platform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(platform: Platform) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    "About Device", style = MaterialTheme.typography.headlineLarge
                )
            }
        )
        Card(
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(
//            containerColor = Color.DarkGray,
//            contentColor = Color.White,
            ),
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                LabelItem("Operation System", "${platform.osName} ${platform.osVersion}")
                LabelItem("Device", platform.deviceModel)
                LabelItem("Density", platform.density.toString())
            }
        }
    }
}

@Composable
fun LabelItem(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = content,
            style = MaterialTheme.typography.titleLarge,
        )
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    MyApplicationTheme {
        AboutScreen(Platform())
    }
}