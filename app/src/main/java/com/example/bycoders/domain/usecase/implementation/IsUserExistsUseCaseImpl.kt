package com.example.bycoders.domain.usecase.implementation

import com.example.bycoders.data.firebase.AuthenticationFirebase
import com.example.bycoders.data.repository.abstraction.AuthenticationRepository
import com.example.bycoders.domain.usecase.abstraction.IsUserExistsUseCase
import javax.inject.Inject

class IsUserExistsUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : IsUserExistsUseCase {

    override fun invoke(
        email: String,
        listener: AuthenticationFirebase.IFirebaseUserExistsListener
    ) {
        authenticationRepository.isUserExists(
            email = email,
            listener = listener
        )
    }
}