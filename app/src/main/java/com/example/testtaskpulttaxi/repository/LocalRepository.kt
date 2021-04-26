package com.example.testtaskpulttaxi.repository

interface LocalRepository {

    fun savePhoneNumber(phoneNumber: String)

    fun getPhoneNumber(): String

    fun saveToken(token: String)

    fun getToken(): String

}