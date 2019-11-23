package com.dpashko.transitionapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.ActivityCountriesListBinding
import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import com.dpashko.transitionapp.model.Status
import dagger.android.AndroidInjection
import javax.inject.Inject

class CountriesListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var view: ActivityCountriesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.activity_countries_list)
        val model = ViewModelProviders.of(this, factory)[CountriesListViewModel::class.java]
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
