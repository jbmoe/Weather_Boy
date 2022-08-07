package com.icecreampablo.weatherapp.domain.repository

import com.icecreampablo.weatherapp.domain.models.Astronomy
import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.models.WeatherForecast
import com.icecreampablo.weatherapp.domain.util.Resource
import java.time.LocalDate

interface WeatherRepository {
    suspend fun getWeatherForecast(
        cityName: String,
        days: Int = 3,
        includeAirQuality: Boolean = false,
        includeWeatherAlert: Boolean = false,
        language: String = "en-US"
    ): Resource<WeatherForecast>

    suspend fun getAstronomyData(
        cityName: String,
        date: LocalDate
    ): Resource<Astronomy>

    suspend fun searchCities(cityName: String): Resource<List<City>>
}