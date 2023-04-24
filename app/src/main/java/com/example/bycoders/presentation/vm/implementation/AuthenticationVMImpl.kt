package com.example.bycoders.presentation.vm.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bycoders.data.firebase.AuthenticationFirebase.IFirebaseAuthListener
import com.example.bycoders.data.firebase.AuthenticationFirebase.IFirebaseUserExistsListener
import com.example.bycoders.domain.usecase.abstraction.CreateUserUseCase
import com.example.bycoders.domain.usecase.abstraction.IsUserExistsUseCase
import com.example.bycoders.domain.usecase.abstraction.IsUserLoggedUseCase
import com.example.bycoders.domain.usecase.abstraction.SignInUseCase
import com.example.bycoders.presentation.vm.abstraction.AuthCommands
import com.example.bycoders.presentation.vm.abstraction.AuthenticationVM
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationVMImpl @Inject constructor(
    private val isUserLoggedUseCase: IsUserLoggedUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val signInUseCase: SignInUseCase,
    private val isUserExistsUseCase: IsUserExistsUseCase
) : AuthenticationVM() {

    private val _authCommandLiveData = MutableLiveData<AuthCommands>()
    override val authCommandLiveData: LiveData<AuthCommands>
        get() = _authCommandLiveData

    override fun createUser(email: String, password: String) {
        createUserUseCase.invoke(
            email = email,
            password = password,
            listener = object : IFirebaseAuthListener {
                override fun onSuccess(loggedUser: FirebaseUser?) {
                    AuthCommands.OnSuccessAuth.run()
                }

                override fun onFailure(e: Exception?) {
                    AuthCommands.OnFailureAuth(e).run()
                }
            }
        )
    }

    override fun signInUser(email: String, password: String) {
        signInUseCase.invoke(
            email = email,
            password = password,
            listener = object : IFirebaseAuthListener {
                override fun onSuccess(loggedUser: FirebaseUser?) {
                    AuthCommands.OnSuccessAuth.run()
                }

                override fun onFailure(e: Exception?) {
                    AuthCommands.OnFailureAuth(e).run()
                }

            }
        )
    }

    override fun isUserLogged() {
        if (isUserLoggedUseCase.invoke()) AuthCommands.UserLogged.run()
    }

    override fun isUserExists(email: String) {
        isUserExistsUseCase.invoke(
            email = email,
            listener = object : IFirebaseUserExistsListener {
                override fun onUserExistsResult(exists: Boolean) {
                    //AuthCommands.UserExistsResult(exists).run()
                }

                override fun onFailure() {
                    AuthCommands.OnFailureAuth().run()
                }

            }
        )
    }

    private fun AuthCommands.run() {
        _authCommandLiveData.postValue(this)
    }

}