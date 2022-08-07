package com.icecreampablo.weatherapp.data.repository

import com.icecreampablo.weatherapp.common.TestData
import com.icecreampablo.weatherapp.data.mappers.mapToAstronomies
import com.icecreampablo.weatherapp.data.mappers.mapToCities
import com.icecreampablo.weatherapp.data.mappers.mapToWeatherForecast
import com.icecreampablo.weatherapp.domain.models.Astronomy
import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.repository.WeatherRepository
import com.icecreampablo.weatherapp.domain.util.Resource
import com.icecreampablo.weatherapp.domain.models.WeatherForecast
import java.time.LocalDate
import javax.inject.Inject

class WeatherRepositoryTestImpl @Inject constructor() : WeatherRepository {

    override suspend fun getWeatherForecast(
        cityName: String,
        days: Int,
        includeAirQuality: Boolean,
        includeWeatherAlert: Boolean,
        language: String
    ): Resource<WeatherForecast> {
        return try {
            Resource.Success(data = TestData.weatherForecastTest.mapToWeatherForecast())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getAstronomyData(cityName: String, date: LocalDate): Resource<Astronomy> {
        return try {
            Resource.Success(data = TestData.astros.mapToAstronomies().first())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun searchCities(cityName: String): Resource<List<City>> {
        return try {
            Resource.Success(data = TestData.cities.mapToCities())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}