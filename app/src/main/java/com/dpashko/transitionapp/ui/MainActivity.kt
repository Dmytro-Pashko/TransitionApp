package com.dpashko.transitionapp.ui

import android.app.Fragment
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dpashko.transitionapp.R
import dagger.android.AndroidInjector
import dagger.android.HasFragmentInjector

class MainActivity : AppCompatActivity(), HasFragmentInjector {

    private lateinit var container: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.list_container)
        showList()
    }

    private fun showList() {
        val fragment =
            supportFragmentManager.findFragmentByTag(CountriesListFragment.TAG)
                ?: CountriesListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.list_container, fragment, CountriesListFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> {

    }

}
