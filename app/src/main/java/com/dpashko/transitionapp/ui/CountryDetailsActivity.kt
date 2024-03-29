package com.dpashko.transitionapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import coil.api.load
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.CountryDetailsBinding
import com.dpashko.transitionapp.model.Country
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class CountryDetailsActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var binding: CountryDetailsBinding
    private var country: Country? = null
    private var isShowPreview = false
    private var mMaxScrollSize = 0

    companion object {
        const val KEY_COUNTRY = "country_key"
        private const val PERCENTAGE_TO_ANIMATE_PREVIEW = 20

        fun start(context: Context, country: Country, bundle: Bundle?) =
            context.startActivity(
                Intent(context, CountryDetailsActivity::class.java)
                    .putExtra(KEY_COUNTRY, country), bundle
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.country_details)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appbar.addOnOffsetChangedListener(this)
        country =
            savedInstanceState?.getParcelable(KEY_COUNTRY) ?: intent.getParcelableExtra(KEY_COUNTRY)
        country?.let { showCountry(it) }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        country?.let {
            outState.putParcelable(KEY_COUNTRY, country)
        }
        super.onSaveInstanceState(outState)
    }

    private fun showCountry(country: Country) {
        title = country.name
        binding.description.text = country.description
        ViewCompat.setTransitionName(binding.preview, country.name)
        binding.background.load(country.background) {
            error(R.drawable.i_error_placeholder)
            placeholder(R.drawable.i_placeholder)
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout.totalScrollRange
        val percentage: Int = abs(i) * 100 / mMaxScrollSize
        if (percentage >= PERCENTAGE_TO_ANIMATE_PREVIEW && isShowPreview) {
            isShowPreview = false
            binding.preview.animate()
                .scaleY(0f)
                .scaleX(0f)
                .setDuration(200)
                .start()
        } else if (percentage <= PERCENTAGE_TO_ANIMATE_PREVIEW && !isShowPreview) {
            isShowPreview = true
            binding.preview.animate()
                .scaleY(1f)
                .scaleX(1f)
                .start()
        }
    }
}
