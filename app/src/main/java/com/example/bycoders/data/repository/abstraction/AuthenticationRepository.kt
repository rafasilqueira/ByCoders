package com.example.bycoders.data.repository.abstraction

import com.example.bycoders.data.firebase.AuthenticationFirebase.IFirebaseAuthListener
import com.example.bycoders.data.firebase.AuthenticationFirebase.IFirebaseUserExistsListener
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {
    fun isUserLogged(): Boolean
    fun isUserExists(email: String, listener: IFirebaseUserExistsListener)
    fun signIn(email: String, password: String, listener: IFirebaseAuthListener)
    fun createUser(email: String, password: String, listener: IFirebaseAuthListener)
    fun getCurrentUser(): FirebaseUser?
}