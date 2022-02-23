package com.example.netflix.ui.login

import com.example.netflix.ui.util.validation.Validity


class LoginInputValidation(
    private val emailValidation: Validity<String> = Validity.EmailValidation(),
    private val passwordValidation: Validity<String> = Validity.PasswordValidation()
) {

    fun isEmailValid(email: String) = emailValidation.isValid(email)

    fun isPasswordValid(password: String) = passwordValidation.isValid(password)
}