package com.dpashko.transitionapp.di

import com.dpashko.transitionapp.api.CountriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApi(): CountriesApi = Retrofit.Builder()
        .baseUrl(CountriesApi.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountriesApi::class.java)
}