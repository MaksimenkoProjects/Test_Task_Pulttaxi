package com.example.testtaskpulttaxi.model


data class UserInfoModel(
    val status: Int,
    val id: Int,
    val phone_number: String,
    val name: String,
    val email: String,
    val sex: String?,
    val birth_day: String?,
    val city: String,
    val rating: String,
    val active_order: String?,
    val organization_id: String?,
    val need_registration: Boolean
)