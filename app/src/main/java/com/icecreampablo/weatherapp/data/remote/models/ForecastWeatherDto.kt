package com.icecreampablo.weatherapp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastWeatherDto (
    @Json(name = "location") var location : LocationDto,
    @Json(name = "current") var current : CurrentDto,
    @Json(name = "forecast") var forecast : ForecastDto
)

@JsonClass(generateAdapter = true)
data class HoursDto (
    @Json(name = "time") var time : String,
    @Json(name = "tempCelsius") var tempCelsius : Double,
    @Json(name = "isDayTime") var isDayTime : Boolean,
    @Json(name = "condition") var condition : ConditionDto,
    @Json(name = "windSpeedKph") var windSpeedKph : Double,
    @Json(name = "windGustKph") var windGustKph : Double,
    @Json(name = "windDirectionDegree") var windDirectionDegree : Double,
    @Json(name = "pressureMb") var pressureMb : Double,
    @Json(name = "precipitationMm") var precipitationMm : Double,
    @Json(name = "humidity") var humidity : Double,
    @Json(name = "feelsLikeCelsius") var feelsLikeCelsius : Double,
    @Json(name = "heatIndexCelsius") var heatIndexCelsius : Double,
    @Json(name = "dewPointCelsius") var dewPointCelsius : Double,
    @Json(name = "itWillRain") var itWillRain : Boolean,
    @Json(name = "chanceOfRain") var chanceOfRain : Double,
    @Json(name = "itWillSnow") var itWillSnow : Boolean,
    @Json(name = "chanceOfSnow") var chanceOfSnow : Double,
    @Json(name = "visibilityKm") var visibilityKm : Double,
    @Json(name = "uv") var uv : Double,
)

@JsonClass(generateAdapter = true)
data class ForecastDto (
    @Json(name = "forecastDays") var forecastDays : List<ForecastDaysDto>
)

@JsonClass(generateAdapter = true)
data class ForecastDaysDto (
    @Json(name = "date") var date: String,
    @Json(name = "maxTempCelsius") var maxTempCelsius: Double,
    @Json(name = "minTempCelsius") var minTempCelsius: Double,
    @Json(name = "avgTempCelsius") var avgTempCelsius: Double,
    @Json(name = "maxWindSpeedKph") var maxWindSpeedKph: Double,
    @Json(name = "totalPrecipitationMm") var totalPrecipitationMm: Double,
    @Json(name = "avgVisibilityKm") var avgVisibilityKm: Double,
    @Json(name = "avgHumidity") var avgHumidity: Double,
    @Json(name = "itWillRain") var itWillRain: Boolean,
    @Json(name = "chanceOfRain") var chanceOfRain: Double,
    @Json(name = "itWillSnow") var itWillSnow: Boolean,
    @Json(name = "chanceOfSnow") var chanceOfSnow: Double,
    @Json(name = "condition") var condition: ConditionDto,
    @Json(name = "uv") var uv: Double,
    @Json(name = "sunrise") var sunrise: String,
    @Json(name = "sunset") var sunset: String,
    @Json(name = "hours") var hours: List<HoursDto>,
)