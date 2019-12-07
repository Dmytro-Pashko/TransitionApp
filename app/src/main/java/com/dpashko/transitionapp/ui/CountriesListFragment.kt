package com.dpashko.transitionapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.CountriesListBinding
import com.dpashko.transitionapp.extension.AndroidInjection
import com.dpashko.transitionapp.extension.playAnimation
import com.dpashko.transitionapp.model.Countries
import com.dpashko.transitionapp.model.Event
import com.dpashko.transitionapp.model.Status
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CountriesListFragment : Fragment(), HasAndroidInjector {

    companion object {
        const val TAG = "CountriesListFragment"

        fun newInstance(): Fragment {
            return CountriesListFragment()
        }
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var binding: CountriesListBinding
    private lateinit var viewModel: CountriesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.countries_list, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, factory)[CountriesListViewModel::class.java]
        viewModel.getCountries().observe(this, Observer<Event<Countries>> { event ->
            updateList(event)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.errorRetryBtn.setOnClickListener {
            showErrorDialog(false)
            viewModel.getCountries(true)
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
        binding.countries.adapter = CountriesAdapter(countries.list)
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

    override fun androidInjector() = androidInjector
}
