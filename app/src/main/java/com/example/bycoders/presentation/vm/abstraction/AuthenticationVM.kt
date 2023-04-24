package com.example.bycoders.presentation.vm.abstraction

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class AuthenticationVM : ViewModel() {
    abstract fun createUser(email: String, password: String)
    abstract fun signInUser(email: String, password: String)
    abstract fun isUserLogged()
    abstract fun isUserExists(email: String)
    abstract val authCommandLiveData: LiveData<AuthCommands>
}

sealed class AuthCommands {
    object OnSuccessAuth : AuthCommands()
    data class OnFailureAuth(val exception: Exception? = null) : AuthCommands()
    object UserLogged : AuthCommands()
}