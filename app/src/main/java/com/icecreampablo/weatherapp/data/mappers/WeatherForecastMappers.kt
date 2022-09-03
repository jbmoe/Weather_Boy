package com.icecreampablo.weatherapp.data.mappers

import com.icecreampablo.weatherapp.common.extensions.kmphToMps
import com.icecreampablo.weatherapp.data.remote.models.CurrentDto
import com.icecreampablo.weatherapp.data.remote.models.ForecastDaysDto
import com.icecreampablo.weatherapp.data.remote.models.ForecastWeatherDto
import com.icecreampablo.weatherapp.domain.models.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun ForecastWeatherDto.mapToWeatherForecast(): WeatherForecast {
    return WeatherForecast(
        location = this.location.mapToWeatherLocation(),
        currentWeather = this.current.mapToCurrentWeather(),
        forecastDays = this.forecast.forecastDays.mapToForecastDays()
    )
}

fun CurrentDto.mapToCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        lastUpdated = LocalDateTime.parse(this.lastUpdated, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        temperatureCelsius = this.tempCelsius,
        windSpeedMps = this.windSpeedKph.kmphToMps().roundToInt(),
        pressureMb = this.pressureMb.roundToInt(),
        humidity = this.humidity.roundToInt(),
        weatherType = this.condition.mapToWeatherType(this.isDayTime),
        windDegree = this.windDirectionDegree.toInt()
    )
}

fun List<ForecastDaysDto>.mapToForecastDays(): List<ForecastDay> {
    return this.map { day ->
        val timeOfSunrise = LocalTime.parse(day.astro.sunrise, DateTimeFormatter.ofPattern("hh:mm a"))
        val timeOfSunset = LocalTime.parse(day.astro.sunset, DateTimeFormatter.ofPattern("hh:mm a"))
        ForecastDay(
            date = LocalDate.parse(day.date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            maxTemperatureCelsius = day.day.maxTempCelsius.roundToInt(),
            minTemperatureCelsius = day.day.minTempCelsius.roundToInt(),
            maxWindSpeedMps = day.day.maxWindSpeedKph.kmphToMps().roundToInt(),
            totalPrecipitationMm = day.day.totalPrecipitationMm,
            avgHumidity = day.day.avgHumidity.roundToInt(),
            weatherType = day.day.condition.mapToWeatherType(),
            timeOfSunrise = timeOfSunrise,
            timeOfSunset = timeOfSunset,
            hours = day.hours.map { hour ->
                val hourTime = LocalDateTime.parse(hour.time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                HourWeather(
                    time = hourTime,
                    timeOfSunrise = if (timeOfSunrise.hour == hourTime.hour) timeOfSunrise else null,
                    timeOfSunset = if (timeOfSunset.hour == hourTime.hour) timeOfSunset else null,
                    temperatureCelsius = hour.tempCelsius.roundToInt(),
                    weatherType = hour.condition.mapToWeatherType(hour.isDayTime),
                    windSpeedMps = hour.windSpeedKph.kmphToMps().roundToInt(),
                    windGustMps = hour.windGustKph.kmphToMps().roundToInt(),
                    precipitationMm = hour.precipitationMm,
                    windDegree = hour.windDirectionDegree.roundToInt()
                )
            }
        )
    }
}