package com.example.bycoders.domain.usecase.implementation

import com.example.bycoders.data.firebase.AuthenticationFirebase
import com.example.bycoders.data.repository.abstraction.AuthenticationRepository
import com.example.bycoders.domain.usecase.abstraction.SignInUseCase
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : SignInUseCase {
    override operator fun invoke(
        email: String,
        password: String,
        listener: AuthenticationFirebase.IFirebaseAuthListener
    ) {
        authenticationRepository.signIn(
            email = email,
            password = password,
            listener = listener
        )
    }
}