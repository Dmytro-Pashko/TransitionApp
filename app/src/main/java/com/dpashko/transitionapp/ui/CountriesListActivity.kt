package com.dpashko.transitionapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.ActivityCountriesListBinding
import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import com.dpashko.transitionapp.model.Status

class CountriesListActivity : AppCompatActivity() {

    private lateinit var view: ActivityCountriesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.activity_countries_list)
        val model = ViewModelProviders.of(this)[CountriesListViewModel::class.java]
        model.getCountries().observe(this, Observer<Event<Countries>> { event ->
            updateList(event)
        })
    }

    private fun updateList(event: Event<Countries>) {
        when (event.status) {
            Status.LOADING -> showLoading()
            Status.SUCCESS -> event.data?.let { showData(it) }
            Status.ERROR -> showError()
        }
    }

    private fun showLoading() {
        view.loading = true
    }

    private fun showData(countries: Countries) {
        view.loading = false
    }

    private fun showError() {
        view.loading = false
    }
}
