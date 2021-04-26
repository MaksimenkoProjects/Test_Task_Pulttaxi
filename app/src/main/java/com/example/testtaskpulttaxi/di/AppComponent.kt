package com.example.testtaskpulttaxi.di

import android.content.Context
import com.example.testtaskpulttaxi.di.annotation.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector


@AppScope
@Component(modules = [AppModule::class])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

}