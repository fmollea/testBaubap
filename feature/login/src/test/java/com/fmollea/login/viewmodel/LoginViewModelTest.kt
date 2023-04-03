package com.fmollea.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fmollea.domain.usecases.login.LoginUseCase
import com.fmollea.domain.usecases.login.LoginUseCaseImpl
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.Spy

class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    @Spy
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = spy<LoginUseCaseImpl>()
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun `login with empty fields return EmptyOrNullFields state`() {
        viewModel.loginUser(EMPTY_EMAIL, EMPTY_PASS)
        val value = viewModel.loginStatus.getOrAwaitValueTest()
        assertThat(value).isEqualTo(LoginViewModel.LoginViewState.EmptyOrNullFields)
    }

    @Test
    fun `login with empty email return EmptyOrNullFields state`() {
        viewModel.loginUser(EMPTY_EMAIL, PASS)
        val value = viewModel.loginStatus.getOrAwaitValueTest()
        assertThat(value).isEqualTo(LoginViewModel.LoginViewState.EmptyOrNullFields)
    }

    @Test
    fun `login with empty password return EmptyOrNullFields state`() {
        viewModel.loginUser(EMAIL, EMPTY_PASS)
        val value = viewModel.loginStatus.getOrAwaitValueTest()
        assertThat(value).isEqualTo(LoginViewModel.LoginViewState.EmptyOrNullFields)
    }

    @Test
    fun `login with null fields return EmptyOrNullFields state`() {
        viewModel.loginUser(null, null)
        val value = viewModel.loginStatus.getOrAwaitValueTest()
        assertThat(value).isEqualTo(LoginViewModel.LoginViewState.EmptyOrNullFields)
    }

    @Test
    fun `login with null email return EmptyOrNullFields state`() {
        viewModel.loginUser(null, PASS)
        val value = viewModel.loginStatus.getOrAwaitValueTest()
        assertThat(value).isEqualTo(LoginViewModel.LoginViewState.EmptyOrNullFields)
    }

    @Test
    fun `login with null password return EmptyOrNullFields state`() {
        viewModel.loginUser(EMAIL, null)
        val value = viewModel.loginStatus.getOrAwaitValueTest()
        assertThat(value).isEqualTo(LoginViewModel.LoginViewState.EmptyOrNullFields)
    }

    @Test
    fun `login with invalid email return InvalidEmail state`() {
        viewModel.loginUser(INVALID_EMAIL, PASS)
        val value = viewModel.loginStatus.getOrAwaitValueTest()
        assertThat(value).isEqualTo(LoginViewModel.LoginViewState.InvalidEmail)
    }

    @Test
    fun `login with valid email and password return Success login `() {
        viewModel.loginUser(EMAIL, PASS)
        val value = viewModel.loginStatus.getOrAwaitValueTest()
        assertThat(value).isEqualTo(LoginViewModel.LoginViewState.Success)
    }

    companion object {
        const val EMPTY_PASS = ""
        const val PASS = "123"

        const val EMPTY_EMAIL = ""
        const val INVALID_EMAIL = "test"
        const val EMAIL = "test@gmail.com"
    }
}