package com.example.bycoders.domain.usecase.abstraction

import com.example.bycoders.data.firebase.AuthenticationFirebase

interface SignInUseCase {
    fun invoke(
        email: String,
        password: String,
        listener: AuthenticationFirebase.IFirebaseAuthListener
    )
}