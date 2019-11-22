package com.dpashko.transitionapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dpashko.transitionapp.ui.CountriesListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CountriesListViewModel::class)
    internal abstract fun bindCountriesListViewModel(viewModel: CountriesListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}

@MapKey
private annotation class ViewModelKey(val value: KClass<out ViewModel>)
