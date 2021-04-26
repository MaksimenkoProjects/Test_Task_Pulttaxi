package com.example.testtaskpulttaxi.di

import androidx.lifecycle.ViewModel
import com.example.testtaskpulttaxi.di.annotation.ViewModelKey
import com.example.testtaskpulttaxi.ui.auth.AuthViewModel
import com.example.testtaskpulttaxi.ui.enter_code.EnterCodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindsAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EnterCodeViewModel::class)
    fun bindsEnterCodeViewModel(viewModel: EnterCodeViewModel): ViewModel

}