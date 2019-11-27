package com.dpashko.transitionapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.api.load
import com.dpashko.transitionapp.R
import com.dpashko.transitionapp.databinding.ActivityCountryDetailsBinding
import com.dpashko.transitionapp.model.Country
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class CountryDetailsActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var view: ActivityCountryDetailsBinding
    private var country: Country? = null
    private var isShowPreview = false
    private var mMaxScrollSize = 0

    companion object {
        const val KEY_COUNTRY = "country_key"
        private const val PERCENTAGE_TO_ANIMATE_PREVIEW = 20

        fun start(context: Context, country: Country) =
            context.startActivity(
                Intent(context, CountryDetailsActivity::class.java)
                    .putExtra(KEY_COUNTRY, country)
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.activity_country_details)
        setSupportActionBar(view.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        view.appbar.addOnOffsetChangedListener(this)
        country =
            savedInstanceState?.getParcelable(KEY_COUNTRY) ?: intent.getParcelableExtra(KEY_COUNTRY)
        country?.let { showCountry(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        country?.let {
            outState.putParcelable(KEY_COUNTRY, country)
        }
        super.onSaveInstanceState(outState)
    }

    private fun showCountry(country: Country) {
        title = country.name
        view.description.text = country.description
        view.preview.load(country.preview) {
            crossfade(true)
            placeholder(R.drawable.ic_image_placeholder)
        }
        view.background.load(country.background) {
            crossfade(true)
            placeholder(R.drawable.ic_image_placeholder)
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout.totalScrollRange
        val percentage: Int = abs(i) * 100 / mMaxScrollSize
        if (percentage >= PERCENTAGE_TO_ANIMATE_PREVIEW && isShowPreview) {
            isShowPreview = false
            view.preview.animate()
                .scaleY(0f)
                .scaleX(0f)
                .setDuration(200)
                .start()
        } else if (percentage <= PERCENTAGE_TO_ANIMATE_PREVIEW && !isShowPreview) {
            isShowPreview = true
            view.preview.animate()
                .scaleY(1f)
                .scaleX(1f)
                .start()
        }
    }
}

