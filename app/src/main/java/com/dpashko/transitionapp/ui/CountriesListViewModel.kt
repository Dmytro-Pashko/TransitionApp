package com.dpashko.transitionapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import com.dpashko.transitionapp.repository.CountryRepository
import kotlinx.coroutines.launch

class CountriesListViewModel : ViewModel() {

    private val countries = MutableLiveData<Event<Countries>>()
    private val repository: CountryRepository = CountryRepository()

    fun getCountries(): LiveData<Event<Countries>> {
        if (countries.value == null) {
            loadCountries()
        }
        return countries
    }

    private fun loadCountries() {
        countries.postValue(Event.Loading())
        viewModelScope.launch {
            val event = repository.getCountries()
            countries.postValue(event)
        }
    }
}
