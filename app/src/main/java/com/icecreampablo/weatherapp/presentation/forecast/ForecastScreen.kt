@file:Suppress("FunctionName")

package com.icecreampablo.weatherapp.presentation.forecast

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.icecreampablo.weatherapp.common.TestData
import com.icecreampablo.weatherapp.data.mappers.mapToCities
import com.icecreampablo.weatherapp.data.mappers.mapToWeatherForecast
import com.icecreampablo.weatherapp.presentation.forecast.composables.SearchContent
import com.icecreampablo.weatherapp.presentation.forecast.composables.TopAppBar
import com.icecreampablo.weatherapp.presentation.forecast.composables.WeatherCard
import com.icecreampablo.weatherapp.presentation.forecast.composables.WeatherForecastDays
import com.icecreampablo.weatherapp.presentation.ui.theme.WeatherAppTheme


@Composable
fun ForecastScreen(viewModel: ForecastViewModel = hiltViewModel()) {
    Content(state = viewModel.state, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(state: ForecastState, onEvent: (Event) -> Unit) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(state.uiState.isLoadingWeather && state.weatherForecast != null),
        onRefresh = { onEvent(ForecastEvent.Refresh) },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(state = state, onEvent = { event ->
                    onEvent(event)
                })
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state.uiState.isSearching) {
                        SearchContent(state, onEvent)
                    } else {
                        ForecastContent(state, onEvent)
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                if (state.uiState.isLoadingWeather && state.weatherForecast == null) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.uiState.error?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

private fun LazyListScope.ForecastContent(
    state: ForecastState,
    onEvent: (Event) -> Unit
) {
    item {
        WeatherCard(state = state)
        Spacer(modifier = Modifier.height(16.dp))
    }
    WeatherForecastDays(state = state, onEvent = onEvent)
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun Preview() {
    val forecast = TestData.weatherForecastTest.mapToWeatherForecast()
    val cities = TestData.cities.mapToCities()
    WeatherAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Content(
                state = ForecastState(
                    weatherForecast = forecast,
                    savedCities = cities.take(3),
                    citySearchResult = cities,
                    uiState = ForecastUiState(
                        isSearching = true,
                        expandedDay = forecast.forecastDays.first(),
                        isLoadingSearch = true
                    )
                )//TestData.cities.map { it.mapToCity() })
            ) {

            }
        }
    }
}