package com.dpashko.transitionapp.repository

import com.dpashko.transitionapp.model.CountriesModel
import com.dpashko.transitionapp.model.Event
import kotlinx.coroutines.delay
import java.net.UnknownHostException

class CountriesRepository {

    suspend fun getCountries(): Event<CountriesModel> {
        delay(5000)
        return Event.Error(UnknownHostException())
    }
}
