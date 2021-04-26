package com.example.testtaskpulttaxi.data.network

import com.example.testtaskpulttaxi.model.AuthenticateClientModel
import com.example.testtaskpulttaxi.model.RequestSmsCodeModel
import com.example.testtaskpulttaxi.model.UserInfoModel
import com.example.testtaskpulttaxi.repository.NetworkRepository
import retrofit2.Response
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
): NetworkRepository {

    override suspend fun sendPhoneNumber(phoneNumber: String): Response<RequestSmsCodeModel>? {
        try {
            val result = apiClient.apiClient.requestSmsCode(phoneNumber).await()
            return result
        }catch (exception: Exception){
            return null
        }
    }

    override suspend fun sendSmsCode(phoneNumber: String, smsCode: String): Response<AuthenticateClientModel>? {
        try {
            val result = apiClient.apiClient.authenticateClient(phoneNumber, smsCode).await()
            return result
        }catch (exception: java.lang.Exception){
            return null
        }
    }

    override suspend fun getUserInfo(token: String): Response<UserInfoModel>? {
        try {
            val result = apiClient.apiClient.getInfo(token).await()
            return result
        }catch (exception: Exception){
            return null
        }
    }
}