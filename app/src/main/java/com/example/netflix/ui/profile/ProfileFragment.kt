package com.example.netflix.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.*
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.example.netflix.R
import com.example.netflix.databinding.ProfileFragmentBinding
import com.example.netflix.model.User
import com.example.netflix.ui.authentication.UserViewModel
import com.example.netflix.ui.login.LoginFragment
import com.example.netflix.ui.util.dataBindings
import com.example.netflix.ui.util.navigateBack

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    val TAG by lazy { this.javaClass.simpleName }
    private val userViewModel by activityViewModels<UserViewModel>()
    private val viewModel by viewModels<ProfileViewModel>()
    private val binding by dataBindings(ProfileFragmentBinding::bind)

    private var firstNavigateToLogin = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: create new profile fragment")

        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle
        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry) { success ->
                Log.d(TAG, "onCreate: $success")
                if (!success) {
                    navController.navigateBack()
                }
            }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userViewModel.loginResult.observe(viewLifecycleOwner) { result ->
            if (!result.success) {
                Log.d(TAG, "onViewCreated: navigate")
                val navController = findNavController()
                navController.navigate(
                    ProfileFragmentDirections.actionToLoginFragment()
                )

            } else {
                result.userView?.let { binding.user = it }
            }

        }
        userViewModel.loginResult.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: $it")
        }
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach: ")
        super.onDetach()
    }

}
