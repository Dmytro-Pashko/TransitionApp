package com.dpashko.transitionapp.repository

import com.dpashko.transitionapp.api.CountriesApi
import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(private val api: CountriesApi) {

    suspend fun getCountries(): Event<Countries> {
        return try {
            val countries = api.getCountries()
            Event.Success(countries)
        } catch (exception: Exception) {
            Event.Error(exception)
        }
    }
}
