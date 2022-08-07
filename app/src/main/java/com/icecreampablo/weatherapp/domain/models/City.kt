package com.icecreampablo.weatherapp.domain.models

data class City(
    val cityName: String,
    val region: String,
    val country: Country
)

data class Country(
    val name: String,
    val code: String?
)
