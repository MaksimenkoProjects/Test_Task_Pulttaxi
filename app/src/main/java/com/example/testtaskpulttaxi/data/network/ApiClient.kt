package com.example.testtaskpulttaxi.data.network

import com.example.testtaskpulttaxi.di.annotation.AppScope
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@AppScope
class ApiClient @Inject constructor() {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://dev.pulttaxi.ru/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val apiClient = retrofit.create(ApiService::class.java)
}