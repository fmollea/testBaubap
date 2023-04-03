package com.fmollea.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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
        observeViewModel()
        binding.btLogin.setOnClickListener {
            loginViewModel.loginUser(binding.txtEmail.text.toString(),
                binding.txtPassword.text.toString())
        }
    }

    private fun observeViewModel() {
        val loginObserver = Observer<LoginViewModel.LoginViewState> {result ->
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
        loginViewModel.loginStatus.observe(viewLifecycleOwner, loginObserver)
    }

    private fun navToLoginDialog(message: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToMessageLoginDialogFragment(message)
        findNavController().navigate(action)
    }
}