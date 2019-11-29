package com.dpashko.transitionapp.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.CountryItemBinding
import com.dpashko.transitionapp.model.Country

class CountriesAdapter(private val activity: Activity, private val countries: List<Country>) :
    RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(activity)
        val binding = CountryItemBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(activity, countries[position])
}

class CountryViewHolder(private val view: CountryItemBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(activity: Activity, country: Country) {
        view.name.text = country.name
        view.description.text = country.description
        view.preview.transitionName = country.name
        view.preview.load(country.preview) {
            error(R.drawable.ic_image_placeholder)
            placeholder(R.drawable.ic_image_placeholder)
        }
        view.root.setOnClickListener {
            val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, view.preview, country.name
            ).toBundle()
            CountryDetailsActivity.start(activity, country, bundle)
        }
        view.executePendingBindings()
    }
}
