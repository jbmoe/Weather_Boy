package com.icecreampablo.weatherapp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AstronomyDto(
    @Json(name = "location") var location : LocationDto,
    @Json(name = "sunrise") var sunrise: String,
    @Json(name = "sunset") var sunset: String,
    @Json(name = "moonset") var moonset: String,
    @Json(name = "moonrise") var moonrise: String
)
