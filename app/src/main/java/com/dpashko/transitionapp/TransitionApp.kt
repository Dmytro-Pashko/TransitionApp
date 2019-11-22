package com.dpashko.transitionapp

import com.dpashko.transitionapp.di.DaggerAppComponent
import dagger.android.DaggerApplication

class TransitionApp : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()
}
