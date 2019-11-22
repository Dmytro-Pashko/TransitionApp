package com.dpashko.transitionapp.di

import com.dpashko.transitionapp.ui.CountriesListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeCountriesListActivity(): CountriesListActivity
}
