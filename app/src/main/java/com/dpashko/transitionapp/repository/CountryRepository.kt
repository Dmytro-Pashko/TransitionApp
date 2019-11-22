package com.dpashko.transitionapp.repository

import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import kotlinx.coroutines.delay
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor() {

    suspend fun getCountries(): Event<Countries> {
        delay(5000)
        return Event.Error(UnknownHostException())
    }
}
