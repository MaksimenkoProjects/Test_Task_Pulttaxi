package com.example.testtaskpulttaxi.data.network

import com.example.testtaskpulttaxi.model.AuthenticateClientModel
import com.example.testtaskpulttaxi.model.RequestSmsCodeModel
import com.example.testtaskpulttaxi.model.UserInfoModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("requestSMSCodeClient")
    fun requestSmsCode(
        @Query("phone_number") phoneNumber: String
    ): Deferred<Response<RequestSmsCodeModel>>


    @POST("authenticateClients")
    fun authenticateClient(
        @Query("phone_number") phone_number: String,
        @Query("password") password: String
    ): Deferred<Response<AuthenticateClientModel>>

    @GET("client/getInfo")
    fun getInfo(
        @Query("token") token: String
    ): Deferred<Response<UserInfoModel>>

}




