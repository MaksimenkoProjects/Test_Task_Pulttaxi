package com.example.testtaskpulttaxi.ui.enter_code

import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskpulttaxi.MainActivity
import com.example.testtaskpulttaxi.data.local.LocalRepositoryImpl
import com.example.testtaskpulttaxi.data.network.NetworkRepositoryImpl
import com.example.testtaskpulttaxi.model.AuthenticateClientModel
import com.example.testtaskpulttaxi.model.UserInfoModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class EnterCodeViewModel @Inject constructor(
    val mainActivity: MainActivity,
    val network: NetworkRepositoryImpl,
    val localData: LocalRepositoryImpl,
    val context: Context
) : ViewModel() {

    val pinCode = MutableLiveData("")
    private val resultSendCode = MutableLiveData<Response<AuthenticateClientModel>?>()
    private val resultUserInfo = MutableLiveData<Response<UserInfoModel>?>()
    val timerText = MutableLiveData("")
    private var timerEnd = false
    private var token: String = ""

    init {
        timer()
        initSendCode()
        initUserInfo()
    }

    private fun timer() {
        timerEnd = false
        val timer = object : CountDownTimer(16000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.value =
                    "Повторно отправить СМС с кодом можно будет \n через ${millisUntilFinished / 1000} секунд"
            }

            override fun onFinish() {
                timerEnd = true
                timerText.value = "Отправить код"
            }
        }
        timer.start()
    }


    fun replaceCode(){
        if (timerEnd) {
            timer()
            viewModelScope.launch { network.sendPhoneNumber(localData.getPhoneNumber()) }
        }
    }


    fun sendCode() {

        viewModelScope.launch {
            resultSendCode.postValue(
                network.sendSmsCode(
                    localData.getPhoneNumber(),
                    pinCode.value.toString()
                )
            )
        }


    }


    private fun initSendCode() {
        resultSendCode.observeForever {
            if (it?.body()?.token != null) {
                token = it.body()!!.token.toString()
                getUserInfo()
            } else if (it?.body()?.error != null) {
                MaterialAlertDialogBuilder(mainActivity)
                    .setTitle("ERROR")
                    .setMessage("Неверный код из смс")
                    .setNeutralButton("повторить") { dialog, which ->
                        viewModelScope.launch(Dispatchers.IO) {
                            network.sendPhoneNumber(localData.getPhoneNumber())
                        }
                        dialog.cancel()
                    }
                    .show()
            } else {
                MaterialAlertDialogBuilder(mainActivity)
                    .setTitle("ERROR")
                    .setMessage("Неизвестная ошибка, попробуйте позже")
                    .setNeutralButton("повторить") { dialog, which ->
                        sendCode()
                        dialog.cancel()
                    }
                    .show()
            }
        }
    }

    private fun initUserInfo() {
        resultUserInfo.observeForever {
            if (it?.code() == 200) {
                openDealogUserInfo(it?.body() as UserInfoModel)
            } else if (it?.code() == 401) {
                MaterialAlertDialogBuilder(mainActivity)
                    .setTitle("ERROR")
                    .setMessage("Неверный ТОКЕН")
                    .setNeutralButton("повторить", null)
                    .show()
            } else {
                MaterialAlertDialogBuilder(mainActivity)
                    .setTitle("ERROR")
                    .setMessage("Неизвестная ошибка, попробуйте позже")
                    .setNeutralButton("повторить") { dialog, which ->
                        getUserInfo()
                        dialog.cancel()
                    }
                    .show()
            }
        }
    }


    private fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            resultUserInfo.postValue(network.getUserInfo(token))
        }
    }


    private fun openDealogUserInfo(userInfo: UserInfoModel) {
        MaterialAlertDialogBuilder(mainActivity)
            .setTitle("UserInfo")
            .setMessage(
                "status: ${userInfo.status}\n" +
                        "id: ${userInfo.id}\n" +
                        "phone_number: ${userInfo.phone_number}\n" +
                        "name: ${userInfo.name}\n" +
                        "email: ${userInfo.email}\n" +
                        "sex: ${userInfo.sex}\n" +
                        "birth_day: ${userInfo.birth_day}\n" +
                        "city: ${userInfo.city}\n" +
                        "rating: ${userInfo.rating}\n" +
                        "active_order: ${userInfo.active_order}\n" +
                        "organization_id: ${userInfo.organization_id}\n" +
                        "need_registration: ${userInfo.need_registration}"
            ).show()
    }


}