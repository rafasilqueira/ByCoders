package com.example.bycoders.domain.usecase.implementation

import com.example.bycoders.data.repository.abstraction.AuthenticationRepository
import com.example.bycoders.domain.usecase.abstraction.IsUserLoggedUseCase
import javax.inject.Inject

class IsUserLoggedUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : IsUserLoggedUseCase {
    override operator fun invoke(): Boolean {
        return authenticationRepository.isUserLogged()
    }
}