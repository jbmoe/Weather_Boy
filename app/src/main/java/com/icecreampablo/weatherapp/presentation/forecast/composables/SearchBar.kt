package com.icecreampablo.weatherapp.presentation.forecast.composables

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.icecreampablo.weatherapp.R
import com.icecreampablo.weatherapp.common.TestData
import com.icecreampablo.weatherapp.data.mappers.mapToCities
import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.presentation.forecast.Event
import com.icecreampablo.weatherapp.presentation.forecast.ForecastEvent
import com.icecreampablo.weatherapp.presentation.forecast.ForecastState
import com.icecreampablo.weatherapp.presentation.forecast.ForecastUiEvent
import com.icecreampablo.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun SearchBar(
    state: ForecastState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    val paddingValue = 16.dp
    TextField(
        value = text,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                text = ""
                onEvent(ForecastUiEvent.ClearSearch)
            }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        },
        placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) },
        onValueChange = {
            text = it
            onEvent(ForecastEvent.SearchCities(it))
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                PaddingValues(
                    start = paddingValue,
                    end = paddingValue,
                    top = paddingValue
                )
            )
    )
    if (state.citySearchResult != null) {
        val cities = state.citySearchResult.union(state.savedCities)
            .sortedBy { !state.savedCities.contains(it) }
        for (city in cities) {
            CityRow(
                city = city,
                isSaved = state.savedCities.contains(city),
                onClick = { city ->
                    ForecastEvent.LoadWeatherForCity(city.cityName)
                }
            )
        }
    }
}

@Composable
private fun CityRow(
    city: City,
    isSaved: Boolean,
    onClick: (City) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .zIndex(1f)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {
                onClick(city)
            }
    ) {
        Row {
            if (isSaved) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            Text(text = city.cityName)
        }
        Text(text = city.country.code ?: city.country.name)
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview() {
    val cities = TestData.cities.mapToCities()
    WeatherAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                SearchBar(
                    state = ForecastState(
                        citySearchResult = cities,
                        savedCities = cities.takeLast(2)
                    ),
                    onEvent = {}
                )
            }
        }
    }
}
