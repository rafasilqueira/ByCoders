package com.example.bycoders.presentation.ui.abstraction

import androidx.fragment.app.Fragment

abstract class AuthenticationFragment : Fragment() {
    abstract fun onLoginPressed()
}