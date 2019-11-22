package com.dpashko.transitionapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.ActivityCountryDetailsBinding
import com.dpashko.transitionapp.model.Country

class CountryDetailsActivity : AppCompatActivity() {

    private lateinit var view: ActivityCountryDetailsBinding
    private var country: Country? = null

    companion object {
        const val KEY_COUNTRY = "country_key"

        fun start(context: Context, country: Country) =
            context.startActivity(
                Intent(context, CountryDetailsActivity::class.java)
                    .putExtra(KEY_COUNTRY, country)
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.activity_country_details)
        country =
            savedInstanceState?.getParcelable(KEY_COUNTRY) ?: intent.getParcelableExtra(KEY_COUNTRY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        country?.let {
            outState.putParcelable(KEY_COUNTRY, country)
        }
        super.onSaveInstanceState(outState)
    }
}
