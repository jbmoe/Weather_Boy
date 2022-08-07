package com.icecreampablo.weatherapp.presentation.forecast.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.models.Country
import com.icecreampablo.weatherapp.presentation.composables.ConditionalDivider
import com.icecreampablo.weatherapp.presentation.forecast.Event
import com.icecreampablo.weatherapp.presentation.forecast.ForecastEvent
import com.icecreampablo.weatherapp.presentation.forecast.ForecastState

fun LazyListScope.SearchContent(state: ForecastState, onEvent: (Event) -> Unit) {
    val cities = state.citySearchResult?.union(state.savedCities)
        ?.sortedBy { !state.savedCities.contains(it) } ?: state.savedCities

    itemsIndexed(cities) { index, city ->
        CitySearchResult(city = city, isSaved = state.savedCities.contains(city), onEvent = onEvent)
        ConditionalDivider(isEnabled = index < cities.lastIndex)
    }

    if (state.uiState.isLoadingSearch) {
        items(10) {
            CitySearchResult(
                isPlaceHolderVisible = true,
                city = City("cityname", "regionname", Country("countryname", "countrycode")),
                onEvent = {})
        }
    }
}


@Composable
private fun CitySearchResult(
    city: City,
    isSaved: Boolean = false,
    isPlaceHolderVisible: Boolean = false,
    onEvent: (Event) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {
                onEvent(ForecastEvent.LoadWeatherForCity(city.cityName))
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isSaved) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            Text(
                modifier = Modifier.placeholder(
                    visible = isPlaceHolderVisible,
                    highlight = PlaceholderHighlight.fade(),
                    color = Color.Gray
                ),
                text = "${city.cityName}, ${city.region}"
            )
        }
        Text(
            modifier = Modifier.placeholder(
                visible = isPlaceHolderVisible,
                highlight = PlaceholderHighlight.fade(),
                color = Color.Gray
            ),
            text = city.country.code ?: city.country.name,
            textAlign = TextAlign.End
        )
    }
}