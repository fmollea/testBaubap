package com.fmollea.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _loginStatus = MutableLiveData<LoginViewState>()
    val loginStatus: LiveData<LoginViewState> = _loginStatus

    fun loginUser(email: String?, password: String?) {
        try {
            if(loginUseCases.areFieldsEmptyOrNulls(email, password)) {
                _loginStatus.postValue(LoginViewState.EmptyOrNullFields)
            } else {
                if (loginUseCases.checkRegexEmail(email)) {
                    loginUseCases.loginUser(email, password)
                    _loginStatus.postValue(LoginViewState.Success)
                } else {
                    _loginStatus.postValue(LoginViewState.InvalidEmail)
                }
            }
        } catch(e: Exception) {
            _loginStatus.postValue(LoginViewState.Error)
        }
    }

    sealed class LoginViewState {
        object Success : LoginViewState()
        object Error : LoginViewState()
        object EmptyOrNullFields : LoginViewState()
        object InvalidEmail : LoginViewState()
    }
}