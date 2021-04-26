package com.example.testtaskpulttaxi.ui.enter_code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskpulttaxi.MainActivity
import com.example.testtaskpulttaxi.R
import com.example.testtaskpulttaxi.databinding.FragmentEnterCodeBinding
import com.example.testtaskpulttaxi.ui.base.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EnterCodeFragment : DaggerFragment() {

    @Inject
    lateinit var mainActivity: MainActivity

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var viewModel: EnterCodeViewModel

    private var _binding: FragmentEnterCodeBinding? = null
    private val binding get() = _binding!!

    private var tempPin = arrayListOf("", "", "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        androidInjector().inject(this)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_enter_code, container, false)
        viewModel = ViewModelProvider(this, factory).get(EnterCodeViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        binding.editNum1.requestFocus()
        initEditTexts()
    }



    private fun initEditTexts(){
        binding.editNum1.addTextChangedListener {
            tempPin[0] = it.toString()
            if (it.toString().length > 0){
                binding.editNum2.requestFocus()
            }
            setNumToPinCode()
        }
        binding.editNum2.addTextChangedListener {
            tempPin[1] = it.toString()
            if (it.toString().length > 0){
                binding.editNum3.requestFocus()
            }else{
                binding.editNum1.requestFocus()
            }
            setNumToPinCode()
        }
        binding.editNum3.addTextChangedListener {
            tempPin[2] = it.toString()
            if (it.toString().length > 0){
                binding.editNum4.requestFocus()
            }else{
                binding.editNum2.requestFocus()
            }
            setNumToPinCode()
        }
        binding.editNum4.addTextChangedListener {
            tempPin[3] = it.toString()
            if (it.toString().length == 0){
                binding.editNum3.requestFocus()
            }
            setNumToPinCode()
        }
    }


    private fun setNumToPinCode(){
        var tempResult = ""
        tempPin.forEach {
            tempResult += it
        }
        viewModel.pinCode.value = tempResult
    }




}