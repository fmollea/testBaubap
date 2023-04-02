package com.fmollea.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.fmollea.domain.usecases.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCase
): ViewModel() {

    fun loginUser(email: String?, password: String?) = liveData(Dispatchers.IO)     {
        try {
            if(loginUseCases.areFieldsEmptyOrNulls(email, password)) {
                emit(LoginViewState.EmptyOrNullFields)
            } else {
                if (loginUseCases.checkRegexEmail(email)) {
                    loginUseCases.loginUser(email, password)
                    emit(LoginViewState.Success)
                } else {
                    emit(LoginViewState.InvalidEmail)
                }
            }
        } catch(e: Exception) {
            emit(LoginViewState.Error)
        }
    }

    sealed class LoginViewState {
        object Success : LoginViewState()
        object Error : LoginViewState()
        object EmptyOrNullFields : LoginViewState()
        object InvalidEmail : LoginViewState()
    }
}