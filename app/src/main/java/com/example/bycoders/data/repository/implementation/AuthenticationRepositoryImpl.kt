package com.example.bycoders.data.repository.implementation

import com.example.bycoders.data.firebase.AuthenticationFirebase
import com.example.bycoders.data.firebase.AuthenticationFirebase.IFirebaseAuthListener
import com.example.bycoders.data.repository.abstraction.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationFirebase: AuthenticationFirebase
) : AuthenticationRepository {

    override fun isUserLogged() = authenticationFirebase.isUserLogged()

    override fun getCurrentUser(): FirebaseUser? = authenticationFirebase.getCurrentUser()

    override fun isUserExists(
        email: String,
        listener: AuthenticationFirebase.IFirebaseUserExistsListener
    ) {
        authenticationFirebase.isUserExists(
            email = email,
            listener = listener
        )
    }

    override fun signIn(
        email: String,
        password: String,
        listener: IFirebaseAuthListener
    ) {
        authenticationFirebase.signIn(
            email = email,
            password = password,
            listener = listener
        )
    }

    override fun createUser(
        email: String,
        password: String,
        listener: IFirebaseAuthListener
    ) {
        authenticationFirebase.createUser(
            email = email,
            password = password,
            listener = listener
        )
    }

}