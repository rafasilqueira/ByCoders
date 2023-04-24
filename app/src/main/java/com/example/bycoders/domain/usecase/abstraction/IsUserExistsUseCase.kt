package com.example.bycoders.domain.usecase.abstraction

import com.example.bycoders.data.firebase.AuthenticationFirebase

interface IsUserExistsUseCase {
    fun invoke(email: String, listener: AuthenticationFirebase.IFirebaseUserExistsListener)
}