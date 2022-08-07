package com.icecreampablo.weatherapp.data.mappers

import com.icecreampablo.weatherapp.data.remote.models.LocationDto
import com.icecreampablo.weatherapp.domain.models.WeatherLocation
import java.time.LocalDateTime

fun LocationDto.mapToWeatherLocation(): WeatherLocation {
    return WeatherLocation(
        city = this.name,
        region = this.region,
        country = this.country,
        time = LocalDateTime.parse(this.localTime),
    )
}