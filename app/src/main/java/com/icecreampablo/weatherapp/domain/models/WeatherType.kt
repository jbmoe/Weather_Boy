package com.icecreampablo.weatherapp.domain.models

import androidx.annotation.DrawableRes
import com.icecreampablo.weatherapp.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    data class ClearSky(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = if (isDayTime) R.drawable.ic_sunny else R.drawable.ic_moon
    )

    data class MainlyClear(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_cloudy
    )

    data class PartlyCloudy(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_cloudy
    )

    data class Overcast(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_cloudy
    )

    data class Foggy(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_very_cloudy
    )

    data class DepositingRimeFog(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_very_cloudy
    )

    data class LightDrizzle(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainshower
    )

    data class ModerateDrizzle(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainshower
    )

    data class DenseDrizzle(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainshower
    )

    data class LightFreezingDrizzle(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_snowyrainy
    )

    data class DenseFreezingDrizzle(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_snowyrainy
    )

    data class SlightRain(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainy
    )

    data class ModerateRain(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainy
    )

    data class HeavyRain(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainy
    )

    data class HeavyFreezingRain(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_snowyrainy
    )

    data class SlightSnowFall(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_snowy
    )

    data class ModerateSnowFall(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_heavysnow
    )

    data class HeavySnowFall(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_heavysnow
    )

    data class SnowGrains(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_heavysnow
    )

    data class SlightRainShowers(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainshower
    )

    data class ModerateRainShowers(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainshower
    )

    data class ViolentRainShowers(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainshower
    )

    data class SlightSnowShowers(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_snowy
    )

    data class HeavySnowShowers(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_snowy
    )

    data class ModerateThunderstorm(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_thunder
    )

    data class SlightHailThunderstorm(val description: String, val isDayTime: Boolean) :
        WeatherType(
            weatherDesc = description,
            iconRes = R.drawable.ic_rainythunder
        )

    data class HeavyHailThunderstorm(val description: String, val isDayTime: Boolean) : WeatherType(
        weatherDesc = description,
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int, isDayTime: Boolean, description: String): WeatherType {
            return when (code) {
                1000 -> ClearSky(description, isDayTime)
                1003, 1006, 1030 -> PartlyCloudy(description, isDayTime)
                1009 -> Overcast(description, isDayTime)
                1063, 1180, 1183, 1186 -> SlightRain(description, isDayTime)
                1066, 1210, 1213, 1255 -> SlightSnowFall(description, isDayTime)
                1069 -> SnowGrains(description, isDayTime)
                1072, 1147, 1168, 1198, 1204, 1249, 1261 -> LightFreezingDrizzle(
                    description,
                    isDayTime
                )
                1087, 1273, 1276 -> ModerateThunderstorm(description, isDayTime)
                1114, 1216, 1219 -> ModerateSnowFall(description, isDayTime)
                1117, 1222 -> HeavySnowFall(description, isDayTime)
                1135 -> Foggy(description, isDayTime)
                1150, 1153 -> LightDrizzle(description, isDayTime)
                1171, 1201, 1207 -> HeavyFreezingRain(description, isDayTime)
                1189 -> ModerateRain(description, isDayTime)
                1192, 1195 -> HeavyRain(description, isDayTime)
                1225, 1258 -> HeavySnowShowers(description, isDayTime)
                1237 -> HeavyHailThunderstorm(description, isDayTime)
                1240 -> SlightRainShowers(description, isDayTime)
                1243 -> ModerateRainShowers(description, isDayTime)
                1246 -> ViolentRainShowers(description, isDayTime)
                1252, 1264 -> HeavyFreezingRain(description, isDayTime)
                else -> ClearSky(description, isDayTime)
            }
        }
    }
}
