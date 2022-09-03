package com.icecreampablo.weatherapp.data.remote

import com.icecreampablo.weatherapp.data.remote.models.AstronomyDto
import com.icecreampablo.weatherapp.data.remote.models.CitySearchResultDto
import com.icecreampablo.weatherapp.data.remote.models.ForecastWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface WeatherApi {
    @GET("v1/forecast.json")
    suspend fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("days") days: Int,
        @Query("aqi") includeAirQualityData: Boolean,
        @Query("alerts") includeWeatherAlert: Boolean,
        @Query("lang") language: String
    ) : ForecastWeatherDto

    @GET("v1/astronomy.json")
    suspend fun getAstronomyData(
        @Query("cityName") cityName: String,
        @Query("date") date: LocalDate
    ): AstronomyDto

    @GET("v1/search.json")
    suspend fun searchCities(
        @Query("q") cityName: String
    ): List<CitySearchResultDto>
}