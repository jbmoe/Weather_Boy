package com.icecreampablo.weatherapp.domain.models

import java.time.LocalDateTime
import java.time.LocalTime

data class WeatherForecast(
    val location: WeatherLocation,
    val currentWeather: CurrentWeather,
    val forecastDays: List<ForecastDay>,
)

data class WeatherLocation(
    val city: String,
    val region: String,
    val country: String,
    val time: LocalDateTime,
)

data class ForecastDay(
    val date: LocalDateTime,
    val maxTemperatureCelsius: Int,
    val minTemperatureCelsius: Int,
    val maxWindSpeedMps: Int,
    val totalPrecipitationMm: Double,
    val avgHumidity: Int,
    val weatherType: WeatherType,
    val timeOfSunrise: LocalTime,
    val timeOfSunset: LocalTime,
    val hours: List<HourWeather>
)

data class HourWeather(
    val time: LocalDateTime,
    val timeOfSunset: LocalTime? = null,
    val timeOfSunrise: LocalTime? = null,
    val temperatureCelsius: Int,
    val weatherType: WeatherType,
    val windSpeedMps: Int,
    val windGustMps: Int,
    val precipitationMm: Double,
    val windDegree: Int
)
