package com.icecreampablo.weatherapp.domain.models

data class City(
    val cityName: String,
    val region: String,
    val country: Country,
    val url: String? = cityName
)

data class Country(
    val name: String,
    val code: String?
)
