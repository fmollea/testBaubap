package com.fmollea.domain.usecases.login

import android.util.Patterns
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(): LoginUseCase {
    override fun loginUser(email: String?, password: String?) = true

    override fun checkRegexEmail(email: String?) = email?.let {
        Patterns.EMAIL_ADDRESS.matcher(it).matches()
    } ?: false

    override fun areFieldsEmptyOrNulls(email: String?, password: String?) = email.isNullOrEmpty() ||
            password.isNullOrEmpty()
}