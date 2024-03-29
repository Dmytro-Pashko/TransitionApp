package com.dpashko.transitionapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import com.dpashko.transitionapp.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountriesListViewModel @Inject constructor(private val repository: CountryRepository) :
    ViewModel() {

    private val countries: MutableLiveData<Event<Countries>> = MutableLiveData()

    fun getCountries(forceUpdate: Boolean = false): LiveData<Event<Countries>> {
        if (forceUpdate || countries.value == null) {
            loadCountries()
        }
        return countries
    }

    private fun loadCountries() {
        countries.postValue(Event.Loading())
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(500)
                val event = repository.getCountries()
                countries.postValue(event)
            }
        }
    }
}
