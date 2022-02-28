package com.example.netflix.data.repository

import com.example.netflix.data.Result
import com.example.netflix.data.model.User
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.*

class UserManager {

    private var user: User? = null
    private var userState: UserStateListener? = null

    fun login(email: String, password: String): Result<User> {
        return try {
            val user = User(UUID.randomUUID().toString(), email).also {
                user = it
            }
            userState?.onUserStateChanged(user)
            Result.Success(user)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        user = null
        userState = null
    }


    fun addUserStateListener(l: UserStateListener) {
        userState = l
    }

    fun removeUserStateListener(l: UserStateListener) {
        if (userState == l) userState = null
        else throw IllegalArgumentException("UserStateListener was not set before!")
    }

    interface UserStateListener {
        fun onUserStateChanged(user: User)
    }
}