package com.icecreampablo.weatherapp.presentation.forecast.composables

import android.content.res.Configuration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.icecreampablo.weatherapp.R
import com.icecreampablo.weatherapp.common.TestData
import com.icecreampablo.weatherapp.common.extensions.mapToCity
import com.icecreampablo.weatherapp.data.mappers.mapToCities
import com.icecreampablo.weatherapp.data.mappers.mapToWeatherForecast
import com.icecreampablo.weatherapp.presentation.forecast.*

@Composable
fun TopAppBar(
    state: ForecastState,
    onEvent: (Event) -> Unit,
) {
    if (state.uiState.isSearching) {
        SmallTopAppBar(
            title = {
                var text by remember { mutableStateOf("") }
                TextField(
                    value = text,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search_placeholder),
                            style = TextStyle.Default.copy(fontSize = 22.sp)
                        )
                    },
                    onValueChange = {
                        text = it
                        onEvent(ForecastEvent.SearchCities(it))
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            actions = {
                IconButton(onClick = { onEvent(ForecastUiEvent.ClearSearch) }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null
                    )
                }
            }
        )
    } else {
        SmallTopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    onEvent(ForecastUiEvent.ToggleIsSearching)
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            },
            title = {
                Text(text = state.weatherForecast?.location?.city ?: "")
            },
            actions = {
                state.weatherForecast?.location?.let { location ->
                    val city = location.mapToCity()
                    val isSaved = state.savedCities.contains(city)
                    IconButton(onClick = {
                        if (isSaved) {
                            onEvent(ForecastEvent.RemoveCity(city))
                        } else {
                            onEvent(ForecastEvent.SaveCity(city))
                        }
                    }) {
                        Icon(
                            imageVector = if (isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            tint = if (isSaved) Color.Red else MaterialTheme.colorScheme.onBackground,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview1() {
    TopAppBar(
        state = ForecastState(
            weatherForecast = TestData.weatherForecastTest.mapToWeatherForecast(),
            savedCities = TestData.cities.mapToCities(),
            uiState = ForecastUiState(isSearching = true)
        )
    ) {

    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun Preview2() {
    TopAppBar(
        state = ForecastState(
            weatherForecast = TestData.weatherForecastTest.mapToWeatherForecast(),
            savedCities = TestData.cities.mapToCities(),
        )
    ) {

    }
}