package com.dpashko.transitionapp.di

import android.app.Application
import com.dpashko.transitionapp.ui.CountriesListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    public fun inject(fragment: CountriesListFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
