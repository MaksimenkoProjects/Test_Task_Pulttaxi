package com.example.testtaskpulttaxi.data.local

import android.content.Context
import com.example.testtaskpulttaxi.MainActivity
import com.example.testtaskpulttaxi.repository.LocalRepository
import javax.inject.Inject


class LocalRepositoryImpl @Inject constructor(mainActivity: MainActivity): LocalRepository {

    private val localData = mainActivity.getPreferences(Context.MODE_PRIVATE)
    private val localDataEditor = localData.edit()


    override fun savePhoneNumber(phoneNumber: String) {
        localDataEditor.putString("user_phone_number", phoneNumber).apply()
    }

    override fun getPhoneNumber(): String {
        return localData.getString("user_phone_number", "").toString()
    }

    override fun saveToken(token: String) {
        localDataEditor.putString("user_token", token).apply()
    }

    override fun getToken(): String {
        return localData.getString("user_token", "").toString()
    }
}