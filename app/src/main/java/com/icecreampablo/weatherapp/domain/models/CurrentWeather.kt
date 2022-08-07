package com.icecreampablo.weatherapp.domain.models

import java.time.LocalDateTime

data class CurrentWeather(
    val lastUpdated: LocalDateTime,
    val temperatureCelsius: Double,
    val weatherType: WeatherType,
    val pressureMb: Int,
    val humidity: Int,
    val windSpeedMps: Int,
    val windDegree: Int
)