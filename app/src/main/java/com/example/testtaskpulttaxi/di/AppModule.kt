package com.example.testtaskpulttaxi.di

import com.example.testtaskpulttaxi.MainActivity
import com.example.testtaskpulttaxi.di.annotation.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    fun mainActivity(): MainActivity

}