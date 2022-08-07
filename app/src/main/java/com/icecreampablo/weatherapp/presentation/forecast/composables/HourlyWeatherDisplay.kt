package com.icecreampablo.weatherapp.presentation.forecast.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icecreampablo.weatherapp.R
import com.icecreampablo.weatherapp.common.TestData
import com.icecreampablo.weatherapp.common.extensions.toPrecipitationDisplayValue
import com.icecreampablo.weatherapp.data.mappers.mapToWeatherForecast
import com.icecreampablo.weatherapp.domain.models.HourWeather
import com.icecreampablo.weatherapp.presentation.ui.theme.WeatherAppTheme
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: HourWeather,
    modifier: Modifier = Modifier
) {
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            style = TextStyle(color = MaterialTheme.colorScheme.onBackground)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            val formattedAstro = remember(weatherData) {
                (weatherData.timeOfSunrise ?: weatherData.timeOfSunset)?.format(
                    DateTimeFormatter.ofPattern("HH:mm")
                )
            }

            if (formattedAstro != null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sunsetrise),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .width(15.dp)
                        .height(15.dp)
                )
            }

            Text(
                text = formattedAstro ?: "",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = weatherData.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${weatherData.temperatureCelsius}°C",
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = MaterialTheme.colorScheme.onBackground)
            )
        }

        Text(
            text = weatherData.precipitationMm.toPrecipitationDisplayValue(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onBackground)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.ic_direction),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .rotate(weatherData.windDegree.toFloat() + 180)
                    .width(10.dp)
                    .height(10.dp)
            )
            Text(
                text = "${weatherData.windSpeedMps} ${stringResource(id = R.string.windspeed_unit)}",
                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview() {
    WeatherAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HourlyWeatherDisplay(
                weatherData = TestData.weatherForecastTest.mapToWeatherForecast().forecastDays.random().hours.random(),
                modifier = Modifier
                    .height(250.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}