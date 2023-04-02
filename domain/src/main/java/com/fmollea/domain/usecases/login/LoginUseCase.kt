package com.fmollea.domain.usecases.login

interface LoginUseCase {
    fun loginUser(email: String?, password:  String?): Boolean
    fun checkRegexEmail(email: String?): Boolean
    fun areFieldsEmptyOrNulls(email: String?, password: String?): Boolean
}