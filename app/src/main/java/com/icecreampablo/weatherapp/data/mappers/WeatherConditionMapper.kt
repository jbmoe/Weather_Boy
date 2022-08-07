package com.icecreampablo.weatherapp.data.mappers

import com.icecreampablo.weatherapp.data.remote.models.ConditionDto
import com.icecreampablo.weatherapp.domain.models.WeatherType

fun ConditionDto.mapToWeatherType(isDayTime: Boolean = true): WeatherType {
    return WeatherType.fromWMO(this.code, isDayTime, this.text)
}