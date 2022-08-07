package com.icecreampablo.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.icecreampablo.weatherapp.presentation.forecast.ForecastScreen
import com.icecreampablo.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.icecreampablo.weatherapp.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.WeatherForecastScreen.route
                    ) {
                        composable(route = Screen.WeatherForecastScreen.route) {
                            ForecastScreen()
                        }
                    }
                }
            }
        }
    }
}