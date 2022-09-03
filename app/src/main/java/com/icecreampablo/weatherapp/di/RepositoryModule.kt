package com.icecreampablo.weatherapp.di

import com.icecreampablo.weatherapp.data.repository.WeatherRepositoryImpl
import com.icecreampablo.weatherapp.data.repository.WeatherRepositoryTestImpl
import com.icecreampablo.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl,
//        weatherRepositoryTestImpl: WeatherRepositoryTestImpl
    ): WeatherRepository
}