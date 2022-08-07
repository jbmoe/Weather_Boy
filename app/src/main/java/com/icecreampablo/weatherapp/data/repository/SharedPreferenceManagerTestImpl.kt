package com.icecreampablo.weatherapp.data.repository

import com.icecreampablo.weatherapp.domain.models.City
import com.icecreampablo.weatherapp.domain.repository.SharedPreferencesManager

class SharedPreferenceManagerTestImpl : SharedPreferencesManager {
    private val savedCities = mutableSetOf<City>()
    private var lastViewedCity: City? = null

    override fun getSavedCities(): List<City> {
        return savedCities.toList()
    }

    override fun saveCity(city: City) {
        savedCities.add(city)
    }

    override fun unSaveCity(city: City) {
        savedCities.remove(city)
    }

    override fun clearAllSavedCities() {
        savedCities.clear()
    }

    override fun saveLastViewedCity(city: City) {
        lastViewedCity = city
    }

    override fun getLastViewedCity(): City? {
        return lastViewedCity
    }
}