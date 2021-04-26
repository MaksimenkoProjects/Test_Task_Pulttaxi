package com.example.testtaskpulttaxi.repository

import com.example.testtaskpulttaxi.model.AuthenticateClientModel
import com.example.testtaskpulttaxi.model.RequestSmsCodeModel
import com.example.testtaskpulttaxi.model.UserInfoModel
import retrofit2.Response

interface NetworkRepository {


    suspend fun sendPhoneNumber(phoneNumber: String): Response<RequestSmsCodeModel>?

    suspend fun sendSmsCode(phoneNumber: String, smsCode: String): Response<AuthenticateClientModel>?

    suspend fun getUserInfo(token: String): Response<UserInfoModel>?
}