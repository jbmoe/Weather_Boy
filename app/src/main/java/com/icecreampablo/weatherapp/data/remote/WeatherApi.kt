package com.icecreampablo.weatherapp.data.remote

import com.icecreampablo.weatherapp.data.remote.models.AstronomyDto
import com.icecreampablo.weatherapp.data.remote.models.CitySearchResultDto
import com.icecreampablo.weatherapp.data.remote.models.ForecastWeatherDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.time.LocalDate

interface WeatherApi {
    @GET("weather/forecast")
    suspend fun getWeatherForecast(
        @Query("cityName") cityName: String,
        @Query("days") days: Int,
        @Query("includeAirQualityData") includeAirQualityData: Boolean,
        @Query("includeWeatherAlert") includeWeatherAlert: Boolean,
        @Header("Accept-Language") language: String,
    ): ForecastWeatherDto

    @GET("weather/astronomy")
    suspend fun getAstronomyData(
        @Query("cityName") cityName: String,
        @Query("date") date: LocalDate
    ): AstronomyDto

    @GET("search/cities")
    suspend fun searchCities(
        @Query("cityName") cityName: String
    ): List<CitySearchResultDto>
}