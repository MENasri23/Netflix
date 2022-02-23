package com.example.netflix.ui.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.netflix.model.User

class UserLiveData(
    private val userManager: UserManager
) : LiveData<User?>(null) {

    private val listener = object : UserManager.UserStateListener {
        override fun onUserStateChanged(user: User) {
            Log.d(this.javaClass.simpleName, "onUserStateChanged: $user")
            value = user
        }
    }

    override fun onActive() {
        userManager.addUserStateListener(listener)
    }

    override fun onInactive() {
        Log.d(this.javaClass.simpleName, "onInactive")
        userManager.removeUserStateListener(listener)
    }


}