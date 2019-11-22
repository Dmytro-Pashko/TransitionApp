package com.dpashko.transitionapp.repository

import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import kotlinx.coroutines.delay
import java.net.UnknownHostException

class CountryRepository {

    suspend fun getCountries(): Event<Countries> {
        delay(5000)
        return Event.Error(UnknownHostException())
    }
}
