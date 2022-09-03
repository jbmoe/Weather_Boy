package com.icecreampablo.weatherapp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastWeatherDto(
    @Json(name = "location") var location: LocationDto,
    @Json(name = "current") var current: CurrentDto,
    @Json(name = "forecast") var forecast: ForecastDto
)

@JsonClass(generateAdapter = true)
data class HoursDto(
    @Json(name = "time") var time: String,
    @Json(name = "temp_c") var tempCelsius: Double,
    @Json(name = "is_day") var isDayTime: Int,
    @Json(name = "condition") var condition: ConditionDto,
    @Json(name = "wind_kph") var windSpeedKph: Double,
    @Json(name = "gust_kph") var windGustKph: Double,
    @Json(name = "wind_degree") var windDirectionDegree: Double,
    @Json(name = "pressure_mb") var pressureMb: Double,
    @Json(name = "precip_mm") var precipitationMm: Double,
    @Json(name = "humidity") var humidity: Double,
    @Json(name = "feelslike_c") var feelsLikeCelsius: Double,
    @Json(name = "heatindex_c") var heatIndexCelsius: Double,
    @Json(name = "dewpoint_c") var dewPointCelsius: Double,
    @Json(name = "will_it_rain") var itWillRain: Int,
    @Json(name = "chance_of_rain") var chanceOfRain: Double,
    @Json(name = "will_it_snow") var itWillSnow: Int,
    @Json(name = "chance_of_snow") var chanceOfSnow: Double,
    @Json(name = "vis_km") var visibilityKm: Double,
    @Json(name = "uv") var uv: Double,
)

@JsonClass(generateAdapter = true)
data class ForecastDto(
    @Json(name = "forecastday") var forecastDays: List<ForecastDaysDto>
)

@JsonClass(generateAdapter = true)
data class ForecastDaysDto(
    @Json(name = "date") var date: String,
    @Json(name = "day") var day: DayDto,
    @Json(name = "astro") var astro: AstroDto,
    @Json(name = "hour") var hours: List<HoursDto>,
)

@JsonClass(generateAdapter = true)
data class DayDto(
    @Json(name = "maxtemp_c") var maxTempCelsius: Double,
    @Json(name = "mintemp_c") var minTempCelsius: Double,
    @Json(name = "avgtemp_c") var avgTempCelsius: Double,
    @Json(name = "maxwind_kph") var maxWindSpeedKph: Double,
    @Json(name = "totalprecip_mm") var totalPrecipitationMm: Double,
    @Json(name = "avgvis_km") var avgVisibilityKm: Double,
    @Json(name = "avghumidity") var avgHumidity: Double,
    @Json(name = "daily_will_it_rain") var itWillRain: Int,
    @Json(name = "daily_chance_of_rain") var chanceOfRain: Double,
    @Json(name = "daily_will_it_snow") var itWillSnow: Int,
    @Json(name = "daily_chance_of_snow") var chanceOfSnow: Double,
    @Json(name = "condition") var condition: ConditionDto,
    @Json(name = "uv") var uv: Double,
)

@JsonClass(generateAdapter = true)
data class AstroDto(
    @Json(name = "sunrise") var sunrise: String,
    @Json(name = "sunset") var sunset: String,
    @Json(name = "moonrise") var moonrise: String,
    @Json(name = "moonset") var moonset: String,
    @Json(name = "moon_phase") var moonPhase: String,
    @Json(name = "moon_illumination") var moonIllumination: String
)