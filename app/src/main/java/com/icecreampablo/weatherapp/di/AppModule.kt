package com.icecreampablo.weatherapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.icecreampablo.weatherapp.data.remote.WeatherApi
import com.icecreampablo.weatherapp.domain.repository.SharedPreferencesManager
import com.icecreampablo.weatherapp.data.repository.SharedPreferencesManagerImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://weatherappruntimeweatherapi20220725123853.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferencesManager {
        val sharedPref = context.getSharedPreferences("weather_boy", MODE_PRIVATE)
        return SharedPreferencesManagerImpl(sharedPref)
    }
}