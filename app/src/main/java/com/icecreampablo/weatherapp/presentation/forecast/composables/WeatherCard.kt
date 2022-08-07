package com.icecreampablo.weatherapp.presentation.forecast.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.icecreampablo.weatherapp.R
import com.icecreampablo.weatherapp.common.TestData
import com.icecreampablo.weatherapp.data.mappers.mapToCities
import com.icecreampablo.weatherapp.data.mappers.mapToWeatherForecast
import com.icecreampablo.weatherapp.presentation.forecast.ForecastState
import com.icecreampablo.weatherapp.presentation.ui.theme.WeatherAppTheme
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    state: ForecastState,
) {
    state.weatherForecast?.currentWeather?.let { currentWeather ->
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp),
            backgroundColor = MaterialTheme.colorScheme.surfaceTint
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${stringResource(R.string.updated)} ${
                            currentWeather.lastUpdated.format(
                                DateTimeFormatter.ofPattern("HH:mm")
                            )
                        }"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = currentWeather.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${currentWeather.temperatureCelsius}${stringResource(R.string.temperature_unit)}",
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = currentWeather.weatherType.weatherDesc,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = currentWeather.pressureMb,
                        unit = stringResource(R.string.pressure_unit),
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure)
                    )
                    WeatherDataDisplay(
                        value = currentWeather.humidity,
                        unit = stringResource(R.string.humidity_unit),
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        WeatherDataDisplay(
                            value = currentWeather.windSpeedMps,
                            unit = stringResource(R.string.windspeed_unit),
                            icon = ImageVector.vectorResource(id = R.drawable.ic_wind)
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_direction),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .rotate(currentWeather.windDegree.toFloat() + 180)
                                .width(15.dp)
                                .height(15.dp)
                        )
                    }
                }
            }
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
            WeatherCard(
                state = ForecastState(
                    weatherForecast = TestData.weatherForecastTest.mapToWeatherForecast(),
                    savedCities = TestData.cities.mapToCities()
                )
            )
        }
    }
}