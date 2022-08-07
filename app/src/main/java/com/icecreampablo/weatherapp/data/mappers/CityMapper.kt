package com.icecreampablo.weatherapp.data.mappers

import com.icecreampablo.weatherapp.data.remote.models.CitySearchResultDto
import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.models.Country

fun CitySearchResultDto.mapToCity(): City {
    return City(this.name, this.region, Country(this.country, null))
}

fun List<CitySearchResultDto>.mapToCities(): List<City> {
    return this.map { it.mapToCity() }
}