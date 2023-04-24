package com.example.bycoders.domain.usecase.implementation

import com.example.bycoders.data.firebase.AuthenticationFirebase.IFirebaseAuthListener
import com.example.bycoders.data.repository.abstraction.AuthenticationRepository
import com.example.bycoders.domain.usecase.abstraction.CreateUserUseCase
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : CreateUserUseCase {
    override operator fun invoke(
        email: String,
        password: String,
        listener: IFirebaseAuthListener
    ) {
        authenticationRepository.createUser(
            email = email,
            password = password,
            listener = listener
        )
    }
}