package com.icecreampablo.weatherapp.presentation.forecast.composables

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icecreampablo.weatherapp.common.TestData
import com.icecreampablo.weatherapp.common.extensions.isLessThan
import com.icecreampablo.weatherapp.common.extensions.toPrecipitationDisplayValue
import com.icecreampablo.weatherapp.data.mappers.mapToWeatherForecast
import com.icecreampablo.weatherapp.domain.models.ForecastDay
import com.icecreampablo.weatherapp.presentation.composables.ConditionalDivider
import com.icecreampablo.weatherapp.presentation.forecast.Event
import com.icecreampablo.weatherapp.presentation.forecast.ForecastState
import com.icecreampablo.weatherapp.presentation.forecast.ForecastUiEvent
import com.icecreampablo.weatherapp.presentation.forecast.ForecastUiState
import com.icecreampablo.weatherapp.presentation.ui.theme.WeatherAppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Suppress("FunctionName")
fun LazyListScope.WeatherForecastDays(
    state: ForecastState,
    onEvent: (Event) -> Unit
) {
    state.weatherForecast?.forecastDays?.let { days ->
        itemsIndexed(days) { index, day ->
            WeatherForecastDay(
                forecastDay = day,
                lastUpdated = state.weatherForecast.currentWeather.lastUpdated,
                isExpanded = state.uiState.expandedDay == day,
                onEvent = onEvent
            )
            ConditionalDivider(
                modifier = Modifier.fillMaxWidth(.95f),
                isEnabled = index < days.lastIndex,
                color = Color.LightGray,
                thickness = .5.dp
            )
        }
    }
}

@Composable
private fun WeatherForecastDay(
    modifier: Modifier = Modifier,
    forecastDay: ForecastDay,
    lastUpdated: LocalDateTime,
    isExpanded: Boolean,
    onEvent: (Event) -> Unit
) {
    DayInfoRow(
        forecastDay = forecastDay,
        isExpanded = isExpanded,
        onEvent = onEvent
    )
    AnimatedVisibility(
        visible = isExpanded
    ) {
        var indexOfCurrentHour = forecastDay.hours.indexOfFirst { hour ->
            hour.time.toLocalDate() == lastUpdated.toLocalDate() && hour.time.hour == lastUpdated.hour
        }
        if (indexOfCurrentHour < 0) indexOfCurrentHour = 0

        val rowState = if (indexOfCurrentHour < 0) {
            LazyListState(firstVisibleItemIndex = forecastDay.hours.lastIndex)
        } else {
            LazyListState(firstVisibleItemIndex = indexOfCurrentHour)
        }

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            state = rowState,
            content = {
                itemsIndexed(forecastDay.hours) { index, weatherData ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        HourlyWeatherDisplay(
                            weatherData = weatherData,
                            modifier = Modifier
                                .height(250.dp)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                        ConditionalDivider(
                            modifier = Modifier
                                .height(225.dp)
                                .width(1.dp),
                            isEnabled = index.isLessThan(forecastDay.hours.lastIndex),
                            color = Color.LightGray,
                            thickness = 5.dp
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun DayInfoRow(
    forecastDay: ForecastDay,
    isExpanded: Boolean,
    onEvent: (Event) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onEvent(ForecastUiEvent.SetExpandedDay(if (isExpanded) null else forecastDay))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(3f),
            text = forecastDay.date.format(DateTimeFormatter.ofPattern("EEE d MMM"))
        )

        Image(
            painter = painterResource(id = forecastDay.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier
                .width(30.dp)
                .weight(1f)
        )

        Text(
            modifier = Modifier
                .weight(3f)
                .padding(horizontal = 16.dp),
            text = forecastDay.totalPrecipitationMm.toPrecipitationDisplayValue(),
            textAlign = TextAlign.End
        )

        Text(
            modifier = Modifier.weight(3f),
            text = "${forecastDay.maxTemperatureCelsius}°/${forecastDay.minTemperatureCelsius}°",
            textAlign = TextAlign.Center
        )

        Icon(
            modifier = Modifier.weight(1f),
            imageVector = if (isExpanded) {
                Icons.Default.KeyboardArrowUp
            } else {
                Icons.Default.KeyboardArrowDown
            },
            contentDescription = null
        )
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun Preview() {
    val data = TestData.weatherForecastTest.mapToWeatherForecast()
    WeatherAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherForecastDays(
                    state = ForecastState(
                        weatherForecast = data,
                        uiState = ForecastUiState(expandedDay = data.forecastDays.first())
                    )
                ) {}
            }
        }
    }
}