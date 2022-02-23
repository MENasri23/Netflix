package com.example.netflix.ui.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * Hide software keyboard if any view already has been focused
 *
 */
fun Activity.hideKeyboard() {
    val inputMethodManager =
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager) ?: return
    currentFocus?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun NavController.navigateBack() {
    val startDestination = graph.startDestinationId
    val navOptions = NavOptions.Builder()
        .setPopUpTo(startDestination, true)
        .build()
    navigate(startDestination, null, navOptions)
}