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
import okhttp3.OkHttpClient
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

    private val httpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor { chain ->
            val url = chain
                .request()
                .url
                .newBuilder()
                .addQueryParameter("key", "793e427980204ebc9de151724220107")
                .build()
            chain.proceed(chain.request().newBuilder().url(url).build())
        }.build()

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
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