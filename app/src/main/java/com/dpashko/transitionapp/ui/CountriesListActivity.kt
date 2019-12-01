package com.dpashko.transitionapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.CountriesListBinding
import com.dpashko.transitionapp.extension.playAnimation
import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import com.dpashko.transitionapp.model.Status
import dagger.android.AndroidInjection
import javax.inject.Inject

class CountriesListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var binding: CountriesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.countries_list)
        val model = ViewModelProviders.of(this, factory)[CountriesListViewModel::class.java]
        model.getCountries().observe(this, Observer<Event<Countries>> { event ->
            updateList(event)
        })
        binding.errorRetryBtn.setOnClickListener {
            showErrorDialog(false)
            model.getCountries(true)
        }
    }

    private fun updateList(event: Event<Countries>) {
        when (event.status) {
            Status.LOADING -> showLoading()
            Status.SUCCESS -> event.data?.let { showData(it) }
            Status.ERROR -> showError()
        }
    }

    private fun showLoading() {
        binding.loading = true
    }

    private fun showData(countries: Countries) {
        binding.loading = false
        binding.countries.adapter = CountriesAdapter(this, countries.list)
    }

    private fun showError() {
        binding.loading = false
        showErrorDialog(true)
    }

    private fun showErrorDialog(show: Boolean) {
        binding.errorContainer.animation?.cancel()
        if (show) {
            binding.errorContainer.playAnimation(R.anim.enter, onAnimationStart = {
                binding.errorContainer.visibility = View.VISIBLE
            })
        } else {
            binding.errorContainer.playAnimation(R.anim.exit, onAnimationEnd = {
                binding.errorContainer.visibility = View.GONE
            })
        }
    }
}
