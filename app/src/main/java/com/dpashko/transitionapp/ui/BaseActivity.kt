package com.dpashko.transitionapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import javax.inject.Inject

//open class BaseActivity<T : ViewModel> : AppCompatActivity() {
//
//    @Inject
//    lateinit var factory: ViewModelProvider.Factory
//    protected var viewModel: T? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
//        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProviders.of(this, factory)[T::class.java]
//    }
//}
