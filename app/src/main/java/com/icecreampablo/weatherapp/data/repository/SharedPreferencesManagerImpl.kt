package com.icecreampablo.weatherapp.data.repository

import android.content.SharedPreferences
import com.icecreampablo.weatherapp.common.extensions.getString
import com.icecreampablo.weatherapp.common.helpers.JsonHelpers
import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.repository.SharedPreferencesManager

class SharedPreferencesManagerImpl(
    private val sharedPreferences: SharedPreferences
) : SharedPreferencesManager {

    private companion object {
        private const val CITIES_LABEL = "cities"
        private const val LAST_VIEWED_LABEL = "last_viewed"
    }

    override fun getSavedCities(): List<City> {
        val savedEncodedCities = sharedPreferences.getString(CITIES_LABEL)

        return JsonHelpers.decode(savedEncodedCities) ?: emptyList()
    }

    override fun saveCity(city: City) {
        val savedEncodedCities = sharedPreferences.getString(CITIES_LABEL)
        val savedCities =
            JsonHelpers.decode<List<City>>(savedEncodedCities)?.toMutableSet() ?: mutableSetOf()

        savedCities.add(city)

        with(sharedPreferences.edit()) {
            putString(CITIES_LABEL, JsonHelpers.encode(savedCities))
            apply()
        }
    }

    override fun unSaveCity(city: City) {
        val savedEncodedCities = sharedPreferences.getString(CITIES_LABEL)
        val savedCities =
            JsonHelpers.decode<List<City>>(savedEncodedCities)?.toMutableSet() ?: mutableSetOf()

        savedCities.remove(city)

        with(sharedPreferences.edit()) {
            putString(CITIES_LABEL, JsonHelpers.encode(savedCities))
            apply()
        }
    }

    override fun clearAllSavedCities() {
        with(sharedPreferences.edit()) {
            putString(CITIES_LABEL, "")
            apply()
        }
    }

    override fun saveLastViewedCity(city: City) {
        with(sharedPreferences.edit()) {
            putString(LAST_VIEWED_LABEL, JsonHelpers.encode(city))
            apply()
        }
    }

    override fun getLastViewedCity(): City? {
        return sharedPreferences.getString(LAST_VIEWED_LABEL, null)?.let { JsonHelpers.decode(it) }
    }
}