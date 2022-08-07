package com.icecreampablo.weatherapp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CitySearchResultDto(
    @Json(name = "name"      ) var name      : String,
    @Json(name = "region"    ) var region    : String,
    @Json(name = "country"   ) var country   : String,
    @Json(name = "latitude"  ) var latitude  : Double,
    @Json(name = "longitude" ) var longitude : Double
)

