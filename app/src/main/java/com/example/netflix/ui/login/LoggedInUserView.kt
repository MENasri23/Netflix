package com.example.netflix.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val email: String,
    val username: String? = null,
    val phoneNumber: String? = null,
    val imageUrl: String? = null
)