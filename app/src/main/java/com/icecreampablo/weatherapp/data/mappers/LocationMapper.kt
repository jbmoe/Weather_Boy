package com.icecreampablo.weatherapp.data.mappers

import com.icecreampablo.weatherapp.data.remote.models.LocationDto
import com.icecreampablo.weatherapp.domain.models.WeatherLocation
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun LocationDto.mapToWeatherLocation(): WeatherLocation {
    return WeatherLocation(
        city = this.name,
        region = this.region,
        country = this.country,
        time = LocalDateTime.parse(this.localTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    )
}