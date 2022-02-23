package com.example.netflix.ui.util.validation

import android.util.Patterns

sealed interface Validity<T> {
    fun isValid(value: T): Boolean

    class UsernameValidation(username: String) : Validity<String> {
        override fun isValid(value: String): Boolean {
            return value.isNotEmpty() && value.first().isLetter()
        }
    }

    class EmailValidation : Validity<String> {
        override fun isValid(value: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(value).matches()
        }
    }

    class PasswordValidation : Validity<String> {

        override fun isValid(value: String): Boolean {
            if (value.isEmpty() ||
                !value.first().isLetterOrDigit()
            ) {
                return false
            }
            // Check other password characteristics

            return true
        }
    }
}
