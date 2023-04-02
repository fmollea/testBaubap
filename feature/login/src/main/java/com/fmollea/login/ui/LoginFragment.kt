package com.fmollea.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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
                    navToLoginDialog(getString(R.string.empty_or_null_fields))
                }
                is LoginViewModel.LoginViewState.InvalidEmail -> {
                    navToLoginDialog(getString(R.string.invalid_email))
                }
                is LoginViewModel.LoginViewState.Success -> {
                    navToLoginDialog(getString(R.string.success))
                }
                is LoginViewModel.LoginViewState.Error -> {
                    navToLoginDialog(getString(R.string.error))
                }
            }
        }
    }

    private fun navToLoginDialog(message: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToMessageLoginDialogFragment(message)
        findNavController().navigate(action)
    }
}