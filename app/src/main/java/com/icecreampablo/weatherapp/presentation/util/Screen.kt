package com.icecreampablo.weatherapp.presentation.util

sealed class Screen(val route: String) {
    object WeatherForecastScreen: Screen("weather_forecast_screen")
}