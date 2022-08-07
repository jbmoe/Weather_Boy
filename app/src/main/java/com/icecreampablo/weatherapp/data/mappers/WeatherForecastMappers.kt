package com.icecreampablo.weatherapp.data.mappers

import com.icecreampablo.weatherapp.common.extensions.kmphToMps
import com.icecreampablo.weatherapp.data.remote.models.CurrentDto
import com.icecreampablo.weatherapp.data.remote.models.ForecastDaysDto
import com.icecreampablo.weatherapp.data.remote.models.ForecastWeatherDto
import com.icecreampablo.weatherapp.domain.models.*
import java.time.LocalDateTime
import java.time.LocalTime
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
        lastUpdated = LocalDateTime.parse(this.lastUpdated),
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
        val timeOfSunrise = LocalTime.parse(day.sunrise)
        val timeOfSunset = LocalTime.parse(day.sunset)
        ForecastDay(
            date = LocalDateTime.parse(day.date),
            maxTemperatureCelsius = day.maxTempCelsius.roundToInt(),
            minTemperatureCelsius = day.minTempCelsius.roundToInt(),
            maxWindSpeedMps = day.maxWindSpeedKph.kmphToMps().roundToInt(),
            totalPrecipitationMm = day.totalPrecipitationMm,
            avgHumidity = day.avgHumidity.roundToInt(),
            weatherType = day.condition.mapToWeatherType(),
            timeOfSunrise = timeOfSunrise,
            timeOfSunset = timeOfSunset,
            hours = day.hours.map { hour ->
                val hourTime = LocalDateTime.parse(hour.time)
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