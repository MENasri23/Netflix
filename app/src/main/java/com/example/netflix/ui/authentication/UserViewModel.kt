package com.example.netflix.ui.authentication

import android.util.Log
import androidx.lifecycle.*
import com.example.netflix.R
import com.example.netflix.data.Result
import com.example.netflix.ui.login.LoggedInUserView
import com.example.netflix.ui.login.LoginResult
import com.example.netflix.ui.util.AppContainer

class UserViewModel : ViewModel() {

    private val userRepository = AppContainer.provideUserRepository()

    private val _loginResult = MutableLiveData(LoginResult())
    val loginResult: LiveData<LoginResult> get() = _loginResult

    fun login(email: String, password: String): LiveData<LoginResult> {
        val result = userRepository.login(email, password)
        _loginResult.value = if (result is Result.Success) {
            LoginResult(userView = LoggedInUserView(result.data!!.email))
        } else {
            LoginResult(error = R.string.login_failed)
        }
        return loginResult
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(javaClass.simpleName, "onCleared...")
    }
}