package com.a1573595.dailypulse.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a1573595.dailypulse.Platform
import com.a1573595.dailypulse.android.screen.AboutScreen
import com.a1573595.dailypulse.android.screen.ArticleListScreen
import com.a1573595.dailypulse.article.ArticleViewModel

enum class DailyPulseScreen {
    ArtistList,
    About
}

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

                    val navController = rememberNavController()
                    val articleViewModel: ArticleViewModel by viewModels()

                    NavHost(
                        navController = navController,
                        startDestination = DailyPulseScreen.ArtistList.name,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        composable(DailyPulseScreen.ArtistList.name) {
                            ArticleListScreen(articleViewModel = articleViewModel) {
                                navController.navigate(DailyPulseScreen.About.name)
                            }
                        }

                        composable(DailyPulseScreen.About.name) {
                            AboutScreen(platform) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
