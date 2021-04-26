package com.example.testtaskpulttaxi.ui.auth

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskpulttaxi.MainActivity
import com.example.testtaskpulttaxi.R
import com.example.testtaskpulttaxi.data.local.LocalRepositoryImpl
import com.example.testtaskpulttaxi.data.network.NetworkRepositoryImpl
import com.example.testtaskpulttaxi.model.RequestSmsCodeModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    val mainActivity: MainActivity,
    val network: NetworkRepositoryImpl,
    val localData: LocalRepositoryImpl
) : ViewModel() {

    val phoneNumber = MutableLiveData("")
    private val resultNetwork = MutableLiveData<Response<RequestSmsCodeModel>?>()
    var temPhone = ""


    init {
        initialization()
    }


    fun sendPhoneNumber() {

        temPhone = phoneNumber.value?.replace(" ", "").toString()
        temPhone = temPhone?.replace("+", "")
        temPhone = temPhone?.replace("-", "")
        if (temPhone?.length == 11) {
            authUser(temPhone)
        } else {
            Toast.makeText(mainActivity, "Вы ввели некоректный номер телефона", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun authUser(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            resultNetwork.postValue(network.sendPhoneNumber(phone))
        }
    }

    private fun initialization(){
        resultNetwork.observeForever {
            if (it?.body()?.status.equals("success")){
                localData.savePhoneNumber(temPhone)
                mainActivity.navController.navigate(R.id.action_authFragment_to_enterCodeFragment)
            }else if (it?.body()?.status.equals("error")){
                Toast.makeText(mainActivity, "С вашим номером что-то не то", Toast.LENGTH_SHORT).show()
            }else{
                MaterialAlertDialogBuilder(mainActivity)
                    .setTitle("ERROR")
                    .setMessage("Неизвестная ошибка, попробуйте позже")
                    .setNeutralButton("повторить"){dialog, which ->
                        sendPhoneNumber()
                        dialog.cancel()
                    }
                    .show()
            }

        }
    }


}