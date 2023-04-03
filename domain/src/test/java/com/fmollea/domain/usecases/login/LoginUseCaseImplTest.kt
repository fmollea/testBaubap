package com.fmollea.domain.usecases.login

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.Spy

class LoginUseCaseImplTest {

    @Spy
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = spy<LoginUseCaseImpl>()
    }

    @Test
    fun `check regex email with valid email`() {
        val result = loginUseCase.checkRegexEmail(EMAIL)
        assertTrue(result)
    }

    @Test
    fun `check regex email with invalid email`() {
        val result = loginUseCase.checkRegexEmail(INVALID_EMAIL)
        assertFalse(result)
    }

    @Test
    fun `check regex email with empty string`() {
        val result = loginUseCase.checkRegexEmail(EMPTY_EMAIL)
        assertFalse(result)
    }

    @Test
    fun `check regex email with null`() {
        val result = loginUseCase.checkRegexEmail(null)
        assertFalse(result)
    }

    @Test
    fun `check are empty fields with empty fields`() {
        val result = loginUseCase.areFieldsEmptyOrNulls(EMPTY_EMAIL, EMPTY_PASS)
        assertTrue(result)
    }

    @Test
    fun `check are empty fields with only email empty`() {
        val result = loginUseCase.areFieldsEmptyOrNulls(EMPTY_EMAIL, PASS)
        assertTrue(result)
    }

    @Test
    fun `check are empty fields with only password empty`() {
        val result = loginUseCase.areFieldsEmptyOrNulls(EMAIL, EMPTY_PASS)
        assertTrue(result)
    }

    @Test
    fun `check are empty fields with fields not empty`() {
        val result = loginUseCase.areFieldsEmptyOrNulls(EMAIL, PASS)
        assertFalse(result)
    }

    @Test
    fun `check empty fields with email null`() {
        val result = loginUseCase.areFieldsEmptyOrNulls(null, PASS)
        assertTrue(result)
    }

    @Test
    fun `check empty fields with password null`() {
        val result = loginUseCase.areFieldsEmptyOrNulls(EMAIL, null)
        assertTrue(result)
    }

    companion object {
        const val EMPTY_PASS = ""
        const val PASS = "123"

        const val EMPTY_EMAIL = ""
        const val INVALID_EMAIL = "test"
        const val EMAIL = "test@gmail.com"
    }
}