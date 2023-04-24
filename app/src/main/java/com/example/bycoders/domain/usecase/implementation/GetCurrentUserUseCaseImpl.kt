package com.example.bycoders.domain.usecase.implementation

import com.example.bycoders.data.repository.abstraction.AuthenticationRepository
import com.example.bycoders.domain.usecase.abstraction.GetCurrentUserUseCase
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUserUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : GetCurrentUserUseCase {
    override fun invoke(): FirebaseUser? {
        return authenticationRepository.getCurrentUser()
    }
}