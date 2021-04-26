package com.example.testtaskpulttaxi.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskpulttaxi.R
import com.example.testtaskpulttaxi.databinding.FragmentAuthBinding
import com.example.testtaskpulttaxi.ui.base.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AuthFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var viewModel: AuthViewModel

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        androidInjector().inject(this)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.phoneInput.addTextChangedListener{

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}