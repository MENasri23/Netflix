package com.example.netflix.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.netflix.R

class LoginViewModel : ViewModel() {

    private val loginValidation by lazy { LoginInputValidation() }

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> get() = _loginForm

    fun loginDataChanged(email: String, password: String) =
        when {
            !loginValidation.isEmailValid(email) -> {
                _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
            }
            !loginValidation.isPasswordValid(password) -> {
                _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
            }
            else -> {
                _loginForm.value = LoginFormState(isDataValid = true)
            }
        }


}