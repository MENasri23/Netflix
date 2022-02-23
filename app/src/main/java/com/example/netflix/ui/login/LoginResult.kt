package com.example.netflix.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult (
     val userView:LoggedInUserView? = null,
     val error:Int? = null
) {
     val success get() = userView != null
}