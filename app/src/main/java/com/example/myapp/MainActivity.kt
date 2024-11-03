package com.example.myapp

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.presentation.ChannelsScreen
import com.example.myapp.presentation.PlayerScreen
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "channels") {
                    composable("channels") {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        ChannelsScreen(navController)
                    }
                    composable("player/{videoUrl}/{startTime}") { backStackEntry ->
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        val videoUrl =
                            backStackEntry.arguments?.getString("videoUrl")?.let { Uri.decode(it) }
                        val startTime =
                            backStackEntry.arguments?.getString("startTime")?.toIntOrNull() ?: 0
                        PlayerScreen(videoUrl ?: "", startTime)
                    }
                }
            }
        }
    }
}