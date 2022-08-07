package com.icecreampablo.weatherapp.domain.models

import java.time.LocalTime

data class Astronomy(
    val location: WeatherLocation,
    var sunrise: LocalTime,
    var sunset: LocalTime,
    var moonset: LocalTime,
    var moonrise: LocalTime,
)
