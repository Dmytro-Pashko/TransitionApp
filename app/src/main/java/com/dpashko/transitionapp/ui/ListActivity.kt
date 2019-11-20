package com.dpashko.transitionapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.ActivityListBinding
import com.dpashko.transitionapp.model.CountriesModel
import com.dpashko.transitionapp.model.Event
import com.dpashko.transitionapp.model.Status

class ListActivity : AppCompatActivity() {

    lateinit var view: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.activity_list)
        val model = ViewModelProviders.of(this)[ListActivityViewModel::class.java]
        model.getCountries().observe(this, Observer<Event<CountriesModel>> { event ->
            updateList(event)
        })
    }

    private fun updateList(event: Event<CountriesModel>) {
        when (event.status) {
            Status.LOADING -> showLoading()
            Status.SUCCESS -> showData()
            Status.ERROR -> showError()
        }
    }

    private fun showLoading() {
        view.loading = true
    }

    private fun showData() {
        view.loading = false
    }

    private fun showError() {
        view.loading = false
    }
}
