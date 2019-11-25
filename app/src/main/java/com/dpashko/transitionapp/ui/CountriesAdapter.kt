package com.dpashko.transitionapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.CountryItemBinding
import com.dpashko.transitionapp.model.Country

class CountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(countries[position])
}

class CountryViewHolder(private val view: CountryItemBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(country: Country) {
        view.name.text = country.name
        view.description.text = country.description
        view.preview.load(country.preview) {
            crossfade(true)
            placeholder(R.drawable.ic_image_placeholder)
        }
        view.executePendingBindings()
    }
}
