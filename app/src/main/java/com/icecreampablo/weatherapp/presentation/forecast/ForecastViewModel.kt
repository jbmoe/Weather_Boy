package com.icecreampablo.weatherapp.presentation.forecast


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icecreampablo.weatherapp.common.extensions.mapToCity
import com.icecreampablo.weatherapp.domain.repository.SharedPreferencesManager
import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.models.ForecastDay
import com.icecreampablo.weatherapp.domain.repository.WeatherRepository
import com.icecreampablo.weatherapp.domain.util.Resource
import com.icecreampablo.weatherapp.presentation.util.extensions.pabloCopy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {
    private var latestSearch: String? = null

    var state by mutableStateOf(ForecastState())
        private set

    init {
        loadWeatherForecast(sharedPreferencesManager.getLastViewedCity()?.url ?: "Aarhus")
        loadSavedCities()
    }

    fun onEvent(event: Event) {
        when (event) {
            is ForecastEvent.Refresh -> refresh()
            is ForecastEvent.SearchCities -> searchCities(event.cityName)
            is ForecastEvent.LoadWeatherForCity -> loadWeatherForecast(event.cityName)
            is ForecastEvent.DeleteCity -> deleteCity(event.city)
            is ForecastEvent.SaveCity -> saveCity(event.city)
            is ForecastEvent.RemoveCity -> removeCity(event.city)

            // UiEvents
            is ForecastUiEvent.ClearSearch -> clearSearch()
            is ForecastUiEvent.ToggleIsSearching -> toggleIsSearching()
            is ForecastUiEvent.SetExpandedDay -> setExpandedDay(event.forecastDay)
        }
    }

    private fun toggleIsSearching() {
        state = state.pabloCopy(isSearching = state.uiState.isSearching.not())
    }

    private fun setExpandedDay(forecastDay: ForecastDay?) {
        state = state.copy(uiState = state.uiState.copy(expandedDay = forecastDay))
    }

    private fun removeCity(city: City) {
        sharedPreferencesManager.unSaveCity(city)
        loadSavedCities()
    }

    private fun saveCity(city: City) {
        sharedPreferencesManager.saveCity(city)
        loadSavedCities()
    }

    private fun deleteCity(city: City) {
        sharedPreferencesManager.unSaveCity(city)
        loadSavedCities()
    }

    private fun refresh() {
        latestSearch?.let { cityName ->
            loadWeatherForecast(
                cityName = cityName,
                onBeforeLoadData = {
                    delay(timeMillis = 500L) // Fake delay for improved UX
                }
            )
        }
    }

    private fun clearSearch() {
        state = state.pabloCopy(citySearchResult = null, isSearching = false)
    }

    private fun loadSavedCities() {
        state = state.pabloCopy(savedCities = sharedPreferencesManager.getSavedCities())
    }

    private fun loadWeatherForecast(
        cityName: String,
        onBeforeLoadData: (suspend CoroutineScope.() -> Unit)? = null
    ) {
        latestSearch = cityName
        viewModelScope.launch {
            state = state.pabloCopy(
                isLoadingWeather = true,
                error = null
            )
            onBeforeLoadData?.invoke(this)
            state = when (val result = repository.getWeatherForecast(
                cityName = cityName,
                language = Locale.getDefault().language
            )) {
                is Resource.Success -> {
                    result.data?.location?.mapToCity()?.let {
                        sharedPreferencesManager.saveLastViewedCity(it)
                    }
                    state.pabloCopy(
                        weatherForecast = result.data,
                        isLoadingWeather = false,
                        error = null,
                        isSearching = false
                    )
                }
                is Resource.Error -> {
                    state.pabloCopy(
                        weatherForecast = null,
                        isLoadingWeather = false,
                        error = result.message,
                        isSearching = false
                    )
                }
            }
        }
    }

    private fun searchCities(cityName: String) {
        if (cityName.isBlank()) {
            return
        }

        viewModelScope.launch {
            state = state.pabloCopy(
                citySearchResult = null,
                isLoadingSearch = true,
            )
            state = when (val result = repository.searchCities(cityName)) {
                is Resource.Success ->
                    state.pabloCopy(
                        citySearchResult = result.data,
                        isLoadingSearch = false,
                        error = null
                    )
                is Resource.Error ->
                    state.pabloCopy(
                        citySearchResult = null,
                        isLoadingSearch = false,
                        error = result.message
                    )
            }
        }
    }
}