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
    @Json(name = "lat") var latitude: Double,
    @Json(name = "lon") var longitude: Double,
    @Json(name = "localtime") var localTime: String
)

@JsonClass(generateAdapter = true)
data class ConditionDto(
    @Json(name = "text") var text: String,
    @Json(name = "icon") var iconUrl: String,
    @Json(name = "code") var code: Int
)

@JsonClass(generateAdapter = true)
data class CurrentDto(
    @Json(name = "last_updated") var lastUpdated: String,
    @Json(name = "temp_c") var tempCelsius: Double,
    @Json(name = "is_day") var isDayTime: Int,
    @Json(name = "condition") var condition: ConditionDto,
    @Json(name = "wind_kph") var windSpeedKph: Double,
    @Json(name = "gust_kph") var windGustKph: Double,
    @Json(name = "wind_degree") var windDirectionDegree: Double,
    @Json(name = "precip_mm") var precipitationMm: Double,
    @Json(name = "pressure_mb") var pressureMb : Double,
    @Json(name = "humidity") var humidity: Double,
    @Json(name = "feelslike_c") var feelsLikeCelsius: Double,
    @Json(name = "vis_km") var visibilityKm: Double,
    @Json(name = "uv") var uv: Double
)