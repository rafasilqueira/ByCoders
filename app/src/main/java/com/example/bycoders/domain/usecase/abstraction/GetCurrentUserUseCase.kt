package com.example.bycoders.domain.usecase.abstraction

import com.google.firebase.auth.FirebaseUser

interface GetCurrentUserUseCase {
    operator fun invoke(): FirebaseUser?
}