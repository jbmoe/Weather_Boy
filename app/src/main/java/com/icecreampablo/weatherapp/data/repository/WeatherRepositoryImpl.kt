package com.icecreampablo.weatherapp.data.repository

import com.icecreampablo.weatherapp.data.mappers.mapToAstronomy
import com.icecreampablo.weatherapp.data.mappers.mapToCities
import com.icecreampablo.weatherapp.data.mappers.mapToWeatherForecast
import com.icecreampablo.weatherapp.data.remote.WeatherApi
import com.icecreampablo.weatherapp.domain.models.Astronomy
import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.repository.WeatherRepository
import com.icecreampablo.weatherapp.domain.util.Resource
import com.icecreampablo.weatherapp.domain.models.WeatherForecast
import java.time.LocalDate
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherForecast(
        cityName: String,
        days: Int,
        includeAirQuality: Boolean,
        includeWeatherAlert: Boolean,
        language: String
    ): Resource<WeatherForecast> {
        return try {
            Resource.Success(
                data = api.getWeatherForecast(
                    cityName = cityName,
                    days = days,
                    includeAirQualityData = false,
                    includeWeatherAlert = false,
                    language = language
                ).mapToWeatherForecast()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getAstronomyData(
        cityName: String,
        date: LocalDate
    ): Resource<Astronomy> {
        return try {
            Resource.Success(
                data = api.getAstronomyData(cityName, date).mapToAstronomy()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun searchCities(cityName: String): Resource<List<City>> {
        return try {
            Resource.Success(
                api.searchCities(cityName).mapToCities()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}