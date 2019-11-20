package com.dpashko.transitionapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dpashko.transitionapp.model.CountriesModel
import com.dpashko.transitionapp.model.Event

class ListActivityViewModel : ViewModel() {

    private val countries = MutableLiveData<Event<CountriesModel>>()

    fun getCountries(): LiveData<Event<CountriesModel>> {
        if (countries.value == null) {
            loadCountries()
        }
        return countries
    }

    private fun loadCountries() {
        countries.postValue(Event.Loading())
        //Do loading
    }
}
