package com.fmollea.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fmollea.login.R
import com.fmollea.login.databinding.FragmentLoginBinding
import com.fmollea.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.btLogin.setOnClickListener {
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        loginViewModel.loginUser(binding.txtEmail.editText?.text.toString(),
            binding.txtPassword.editText?.text.toString()).observe(viewLifecycleOwner) { result ->
            when(result) {
                is LoginViewModel.LoginViewState.EmptyOrNullFields -> {

                }
                is LoginViewModel.LoginViewState.InvalidEmail -> {

                }
                is LoginViewModel.LoginViewState.Success -> {

                }
                is LoginViewModel.LoginViewState.Error -> {

                }
            }
        }
    }
}