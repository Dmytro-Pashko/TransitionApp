package com.dpashko.transitionapp.di

import com.dpashko.transitionapp.ui.CountriesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeCountriesListFragment(): CountriesListFragment
}
