package com.icecreampablo.weatherapp.data.mappers

import com.icecreampablo.weatherapp.data.remote.models.AstronomyDto
import com.icecreampablo.weatherapp.domain.models.Astronomy
import java.time.LocalTime

fun AstronomyDto.mapToAstronomy(): Astronomy {
    return Astronomy(
        this.location.mapToWeatherLocation(),
        LocalTime.parse(this.sunrise),
        LocalTime.parse(this.sunset),
        LocalTime.parse(this.moonset),
        LocalTime.parse(this.moonrise)
    )
}

fun List<AstronomyDto>.mapToAstronomies(): List<Astronomy> {
    return this.map { it.mapToAstronomy() }
}