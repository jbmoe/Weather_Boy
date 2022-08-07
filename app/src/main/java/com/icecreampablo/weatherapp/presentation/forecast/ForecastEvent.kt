package com.icecreampablo.weatherapp.presentation.forecast

import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.models.ForecastDay

sealed class ForecastEvent : Event {
    object Refresh : ForecastEvent()

    data class SearchCities(val cityName: String) : ForecastEvent()
    data class LoadWeatherForCity(val cityName: String) : ForecastEvent()
    data class SaveCity(val city: City) : ForecastEvent()
    data class DeleteCity(val city: City) : ForecastEvent()
    data class RemoveCity(val city: City) : ForecastEvent()
}

sealed class ForecastUiEvent : Event {
    data class SetExpandedDay(val forecastDay: ForecastDay?) : ForecastEvent()
    object ClearSearch : ForecastUiEvent()
    object ToggleIsSearching : ForecastUiEvent()
}

sealed interface Event