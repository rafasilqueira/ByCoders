package com.example.bycoders.presentation.ui.implementation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bycoders.R
import com.example.bycoders.databinding.AuthFragmentBinding
import com.example.bycoders.presentation.extensions.isEmailValid
import com.example.bycoders.presentation.ui.abstraction.AuthenticationFragment
import com.example.bycoders.presentation.vm.abstraction.AuthCommands.OnFailureAuth
import com.example.bycoders.presentation.vm.abstraction.AuthCommands.OnSuccessAuth
import com.example.bycoders.presentation.vm.abstraction.AuthCommands.UserLogged
import com.example.bycoders.presentation.vm.implementation.AuthenticationVMImpl
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

private const val AUTHENTICATION_FRAGMENT = "AuthenticationFragment"

@AndroidEntryPoint
class AuthenticationFragmentImpl : AuthenticationFragment() {

    private lateinit var binding: AuthFragmentBinding
    private val mAuthViewModel by viewModels<AuthenticationVMImpl>()
    private val crashlytics by lazy { Firebase.crashlytics }
    private val analytics by lazy { Firebase.analytics }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthFragmentBinding.inflate(inflater, container, false)
        return binding.run {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@AuthenticationFragmentImpl
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setObservers()
        checkUserIsLogged()
    }

    private fun checkUserIsLogged() {
        mAuthViewModel.isUserLogged()
    }

    private fun setObservers() {
        mAuthViewModel.authCommandLiveData.observe(viewLifecycleOwner) {
            when (it) {
                UserLogged, OnSuccessAuth -> {
                    analytics.logEvent(AUTHENTICATION_FRAGMENT, bundleOf("auth_event" to "Success"))
                    goToMaps()
                }
                is OnFailureAuth -> onFailureAction(it.exception)
            }
        }
    }

    private fun setupUI() {
        with(binding) {
            edtEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    setEmailStatus(s.toString().isEmailValid())
                }
            })

            edtEmail.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) setEmailStatus(isEmailValid())
            }
        }
    }

    private fun setEmailStatus(isValid: Boolean) {
        binding.inputLayoutEmail.error = when {
            isValid -> null
            else -> getString(R.string.fragment_login_invalid_email)
        }
    }

    private fun isEmailValid() = binding.edtEmail.text.toString().isEmailValid()
    private fun getEmail() = binding.edtEmail.text.toString()
    private fun getPassword() = binding.edtPassword.text.toString()

    private fun onFailureAction(exception: Exception?) {
        crashlytics.setCustomKey(
            "AUTHENTICATION_FRAGMENT",
            "Login failed reason => ${exception?.message}"
        )
    }

    override fun onLoginPressed() {
        mAuthViewModel.signInUser(
            email = getEmail(),
            password = getPassword()
        )
    }

    private fun goToMaps() {
        findNavController().navigate(AuthenticationFragmentImplDirections.toMap())
    }

}