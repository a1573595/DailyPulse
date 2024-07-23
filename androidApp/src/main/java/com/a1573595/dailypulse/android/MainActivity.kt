package com.a1573595.dailypulse.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a1573595.dailypulse.Platform

class MainActivity : ComponentActivity() {
    private val platform = Platform()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platform.logSystemInfo()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AboutView(platform)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutView(platform: Platform) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    "About Device",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        )
        Card(
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(
//            containerColor = Color.DarkGray,
//            contentColor = Color.White,
            )
        ) {
            LabelItem("Operation System", "${platform.osName} ${platform.osVersion}")
            LabelItem("Device", platform.deviceModel)
            LabelItem("Density", platform.density.toString())
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

@Preview
@Composable
fun AboutViewPreview() {
    MyApplicationTheme {
        AboutView(Platform())
    }
}
