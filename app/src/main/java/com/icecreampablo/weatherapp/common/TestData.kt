package com.icecreampablo.weatherapp.common

import com.icecreampablo.weatherapp.data.remote.models.*
import net.datafaker.Faker
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


object TestData {
    private val faker = Faker()
    private val currentTime: LocalDateTime = LocalDateTime.now().plusHours(21)
    private val dateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    private val currentTimeFormatted: String = currentTime.format(dateTimeFormatter)

    val weatherForecastTest = ForecastWeatherDto(
        location = LocationDto(
            name = "Videbæk",
            region = "Midtjylland",
            country = "Danmark",
            latitude = faker.address().latitude().toDouble(),
            longitude = faker.address().longitude().toDouble(),
            localTime = currentTimeFormatted
        ),
        current = CurrentDto(
            lastUpdated = currentTimeFormatted,
            tempCelsius = 15.0,
            isDayTime = 1,
            condition = getRandomConditionDto(),
            windSpeedKph = 24.1,
            windGustKph = 300.0,
            windDirectionDegree = Random.nextDouble(0.0, 360.0),
            precipitationMm = 82.0,
            pressureMb = 1012.0,
            humidity = 12.6,
            feelsLikeCelsius = 10.0,
            visibilityKm = 49.0,
            uv = 4.0
        ),
        forecast = ForecastDto(
            forecastDays = (0..5).map { day ->
                ForecastDaysDto(
                    date = currentTime.with(LocalTime.MIN).format(dateTimeFormatter),
                    day = DayDto(
                        maxTempCelsius = Random.nextDouble(0.0, 30.0),
                        minTempCelsius = Random.nextDouble(0.0, 30.0),
                        avgTempCelsius = Random.nextDouble(0.0, 30.0),
                        maxWindSpeedKph = Random.nextDouble(0.0, 50.0),
                        totalPrecipitationMm = Random.nextDouble(0.0, 20.0),
                        avgVisibilityKm = 10.0,
                        avgHumidity = 71.0,
                        itWillRain = 1,
                        chanceOfRain = 97.0,
                        itWillSnow = 0,
                        chanceOfSnow = 0.0,
                        condition = getRandomConditionDto(),
                        uv = 3.0,
                    ),
                    astro = AstroDto(
                        sunrise = "05:20 am",
                        sunset = "09:46 pm",
                        moonset = "03:40 PM",
                        moonrise = "10:16 PM",
                        moonPhase = "First Quarter",
                        moonIllumination = "49"
                    ),
                    hours = (0 until 24).map { hour ->
                        HoursDto(
                            time = currentTime.with(LocalTime.MIN).plusDays(day.toLong())
                                .withHour(hour).format(dateTimeFormatter),
                            tempCelsius = Random.nextDouble(0.0, 30.0),
                            isDayTime = if ((6..22).contains(hour)) 1 else 0,
                            condition = getRandomConditionDto(),
                            windSpeedKph = Random.nextDouble(0.0, 50.0),
                            windGustKph = Random.nextDouble(0.0, 500.0),
                            windDirectionDegree = Random.nextDouble(0.0, 360.0),
                            pressureMb = Random.nextDouble(900.0, 1100.0),
                            precipitationMm = Random.nextDouble(0.0, 20.0),
                            humidity = Random.nextDouble(0.0, 100.0),
                            feelsLikeCelsius = Random.nextDouble(-5.0, 25.0),
                            heatIndexCelsius = Random.nextDouble(-5.0, 25.0),
                            dewPointCelsius = Random.nextDouble(0.0, 10.0),
                            itWillRain = Random.nextInt(2),
                            chanceOfRain = Random.nextDouble(0.0, 100.0),
                            itWillSnow = Random.nextInt(2),
                            chanceOfSnow = Random.nextDouble(0.0, 100.0),
                            visibilityKm = Random.nextDouble(0.0, 50.0),
                            uv = Random.nextDouble(0.0, 8.0),
                        )
                    }
                )
            }
        )
    )

    private fun getRandomConditionDto(): ConditionDto {
        return listOf(
            1000,
            1003,
            1006,
            1009,
            1300
        ).map {
            ConditionDto(faker.weather().description(), "", it)
        }.random()
    }

    val cities = (0..10).map {
        val address = faker.address()
        val name = address.cityName()
        CitySearchResultDto(
            name = name,
            region = address.state(),
            country = address.country(),
            latitude = address.latitude().toDouble(),
            longitude = address.longitude().toDouble(),
            url = name
        )
    }

    val astros = weatherForecastTest.let { forecast ->
        forecast.forecast.forecastDays.map { day ->
            AstronomyDto(
                location = forecast.location,
                sunrise = "05:01:00",
                sunset = "21:49:00",
                moonset = "13:58:00",
                moonrise = "00:13:00"
            )
        }
    }

//    listOf<CitySearchResultDto>(
//        CitySearchResultDto(
//            name = "Videbæk",
//            region = "Midtjylland",
//            country = "Danmark",
//            longitude = 123.123,
//            latitude = 123.123
//        ),
//        CitySearchResultDto(
//            name = "Aarhus",
//            region = "Midtjylland",
//            country = "Danmark",
//            longitude = 123.123,
//            latitude = 123.123
//        ),
//        CitySearchResultDto(
//            name = "Herning",
//            region = "Midtjylland",
//            country = "Danmark",
//            longitude = 123.123,
//            latitude = 123.123
//        ),
//        CitySearchResultDto(
//            name = "Ringkøbing",
//            region = "Midtjylland",
//            country = "Danmark",
//            longitude = 123.123,
//            latitude = 123.123
//        ),
//        CitySearchResultDto(
//            name = "Vejle",
//            region = "Midtjylland",
//            country = "Danmark",
//            longitude = 123.123,
//            latitude = 123.123
//        ),
//    )
}