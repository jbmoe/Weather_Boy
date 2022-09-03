package com.icecreampablo.weatherapp.data.mappers

import com.icecreampablo.weatherapp.data.remote.models.ConditionDto
import com.icecreampablo.weatherapp.domain.models.WeatherType

fun ConditionDto.mapToWeatherType(isDayTime: Int = 1): WeatherType {
    return WeatherType.fromWMO(this.code, isDayTime == 1, this.text)
}