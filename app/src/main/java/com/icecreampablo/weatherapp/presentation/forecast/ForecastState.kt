package com.icecreampablo.weatherapp.presentation.forecast

import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.models.ForecastDay
import com.icecreampablo.weatherapp.domain.models.WeatherForecast

data class ForecastState(
    val weatherForecast: WeatherForecast? = null,
    val citySearchResult: List<City>? = null,
    val savedCities: List<City> = emptyList(),
    val uiState: ForecastUiState = ForecastUiState(),
)

data class ForecastUiState(
    val isSearching: Boolean = false,
    val isLoadingWeather: Boolean = false,
    val isLoadingSearch: Boolean = false,
    val expandedDay: ForecastDay? = null,
    val error: String? = null
)
