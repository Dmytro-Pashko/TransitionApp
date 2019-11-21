package com.dpashko.transitionapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpashko.transitionapp.model.CountriesModel
import com.dpashko.transitionapp.model.Event
import com.dpashko.transitionapp.repository.CountriesRepository
import kotlinx.coroutines.launch

class ListActivityViewModel : ViewModel() {

    private val countries = MutableLiveData<Event<CountriesModel>>()
    private val repository: CountriesRepository = CountriesRepository()

    fun getCountries(): LiveData<Event<CountriesModel>> {
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
