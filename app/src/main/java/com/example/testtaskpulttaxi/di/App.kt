package com.example.testtaskpulttaxi.di

import com.example.testtaskpulttaxi.di.annotation.AppScope
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@AppScope
class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().context(this).build()
    }
}