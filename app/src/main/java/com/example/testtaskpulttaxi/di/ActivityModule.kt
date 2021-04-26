package com.example.testtaskpulttaxi.di

import com.example.testtaskpulttaxi.di.annotation.FragmentScope
import com.example.testtaskpulttaxi.ui.auth.AuthFragment
import com.example.testtaskpulttaxi.ui.enter_code.EnterCodeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    fun authFragment(): AuthFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    fun enterCodeFragment(): EnterCodeFragment


}