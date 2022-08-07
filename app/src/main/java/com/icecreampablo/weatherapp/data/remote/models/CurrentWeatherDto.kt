package com.icecreampablo.weatherapp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherDto(
    @Json(name = "location") var location: LocationDto,
    @Json(name = "current") var current: CurrentDto
)

@JsonClass(generateAdapter = true)
data class LocationDto(
    @Json(name = "name") var name: String,
    @Json(name = "region") var region: String,
    @Json(name = "country") var country: String,
    @Json(name = "latitude") var latitude: Double,
    @Json(name = "longitude") var longitude: Double,
    @Json(name = "localTime") var localTime: String,
)

@JsonClass(generateAdapter = true)
data class ConditionDto(
    @Json(name = "text") var text: String,
    @Json(name = "iconUrl") var iconUrl: String,
    @Json(name = "code") var code: Int,
)

@JsonClass(generateAdapter = true)
data class CurrentDto(
    @Json(name = "lastUpdated") var lastUpdated: String,
    @Json(name = "tempCelsius") var tempCelsius: Double,
    @Json(name = "isDayTime") var isDayTime: Boolean,
    @Json(name = "condition") var condition: ConditionDto,
    @Json(name = "windSpeedKph") var windSpeedKph: Double,
    @Json(name = "windGustKph") var windGustKph: Double,
    @Json(name = "windDirectionDegree") var windDirectionDegree: Double,
    @Json(name = "precipitationMm") var precipitationMm: Double,
    @Json(name = "pressureMb") var pressureMb : Double,
    @Json(name = "humidity") var humidity: Double,
    @Json(name = "feelsLikeCelsius") var feelsLikeCelsius: Double,
    @Json(name = "visibilityKm") var visibilityKm: Double,
    @Json(name = "uv") var uv: Double,
)