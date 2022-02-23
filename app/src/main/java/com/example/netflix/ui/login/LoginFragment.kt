package com.example.netflix.ui.login

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController

import com.example.netflix.R
import com.example.netflix.databinding.LoginFragmentBinding
import com.example.netflix.ui.authentication.UserViewModel
import com.example.netflix.ui.home.HomeFragment.Companion.TAG
import com.example.netflix.ui.profile.ProfileFragment
import com.example.netflix.ui.util.hideKeyboard

class LoginFragment : Fragment() {

    private val userViewModel by activityViewModels<UserViewModel>()
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: LoginFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)

        loginViewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        val emailEditText = binding.email
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    emailEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                loginViewModel.loginDataChanged(email, password)
            }
        }
        emailEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
                return@setOnEditorActionListener true
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            login(
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

    private fun login(email: String, password: String) {
        userViewModel.login(email, password).observe(viewLifecycleOwner) { result ->
            if (result.success) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                updateUiWithUser(result.userView!!)
                requireActivity().hideKeyboard()

                findNavController().popBackStack()
            } else {
                result.error?.let { errorResId -> showLoginFailed(errorResId) }
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + (model.username ?: model.email)
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ProfileFragment", "onDestroy: login fragment ")
    }

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }
}