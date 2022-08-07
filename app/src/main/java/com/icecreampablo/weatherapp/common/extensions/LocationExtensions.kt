package com.icecreampablo.weatherapp.common.extensions

import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.models.Country
import com.icecreampablo.weatherapp.domain.models.WeatherLocation
import java.util.*
import kotlin.collections.HashMap

fun WeatherLocation.mapToCity(): City {
    return City(
        cityName = this.city,
        region = this.region,
        country = Country(name = this.country, code = getCountryCode(this.country))
    )
}

private fun getCountryCode(countryName: String): String? {

    // Get all country codes in a string array.
    val isoCountryCodes: Array<String> = Locale.getISOCountries()
    val countryMap: MutableMap<String, String> = HashMap()
    var locale: Locale
    var name: String

    // Iterate through all country codes:
    for (code in isoCountryCodes) {
        // Create a locale using each country code
        locale = Locale("", code)
        // Get country name for each code.
        name = locale.displayCountry
        // Map all country names and codes in key - value pairs.
        countryMap[name] = code
    }

    // Return the country code for the given country name using the map.
    // Here you will need some validation or better yet
    // a list of countries to give to user to choose from.
    return countryMap[countryName] // "NL" for Netherlands.
}