package com.icecreampablo.weatherapp.domain.repository

import com.icecreampablo.weatherapp.domain.models.City

interface SharedPreferencesManager {
    fun getSavedCities(): List<City>
    fun saveCity(city: City)
    fun unSaveCity(city: City)
    fun clearAllSavedCities()

    fun saveLastViewedCity(city: City)
    fun getLastViewedCity() : City?
}