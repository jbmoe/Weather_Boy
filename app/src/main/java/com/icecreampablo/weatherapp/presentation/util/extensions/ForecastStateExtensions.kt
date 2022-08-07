package com.icecreampablo.weatherapp.presentation.util.extensions

import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.models.ForecastDay
import com.icecreampablo.weatherapp.domain.models.WeatherForecast
import com.icecreampablo.weatherapp.presentation.forecast.ForecastState

fun ForecastState.toggleIsSearching(value: Boolean? = null): ForecastState {
    return this.copy(
        uiState = this.uiState.copy(isSearching = value ?: this.uiState.isSearching.not())
    )
}

fun ForecastState.pabloCopy(
    weatherForecast: WeatherForecast? = null,
    citySearchResult: List<City>? = null,
    savedCities: List<City>? = null,
    isSearching: Boolean? = null,
    isLoadingWeather: Boolean? = null,
    isLoadingSearch: Boolean? = null,
    expandedDay: ForecastDay? = null,
    error: String? = null
): ForecastState {
    var state = this.copy(
        citySearchResult = citySearchResult,
        uiState = this.uiState.copy(error = error)
    )

    if (weatherForecast != null) {
        state = state.copy(weatherForecast = weatherForecast)
    }
    if (savedCities != null) {
        state = state.copy(savedCities = savedCities)
    }
    if (isSearching != null) {
        state = state.copy(uiState = state.uiState.copy(isSearching = isSearching))
    }
    if (isLoadingWeather != null) {
        state = state.copy(uiState = state.uiState.copy(isLoadingWeather = isLoadingWeather))
    }
    if (isLoadingSearch != null) {
        state = state.copy(uiState = state.uiState.copy(isLoadingSearch = isLoadingSearch))
    }
    if (expandedDay != null) {
        state = state.copy(uiState = state.uiState.copy(expandedDay = expandedDay))
    }

    return state
}
