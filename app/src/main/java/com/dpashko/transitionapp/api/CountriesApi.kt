package com.dpashko.transitionapp.api

import com.dpashko.transitionapp.model.Countries
import retrofit2.http.GET

interface CountriesApi {

    companion object{
        const val BASE_API_URL = "https://raw.githubusercontent.com"
    }
    @GET("/Dmytro-Pashko/TransitionApp/master/raw/countries.json")
    suspend fun getCountries(): Countries
}
