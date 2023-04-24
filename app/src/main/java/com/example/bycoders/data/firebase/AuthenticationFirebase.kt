package com.example.bycoders.data.firebase

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationFirebase {

    private val auth = Firebase.auth

    fun createUser(
        email: String,
        password: String,
        listener: IFirebaseAuthListener
    ) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    when {
                        it.isSuccessful -> listener.onSuccess(auth.currentUser)
                        else -> {
                            listener.onFailure(it.exception)
                        }
                    }
                }
        } catch (e: Exception) {
            listener.onFailure(e)
        }
    }

    fun signIn(
        email: String,
        password: String,
        listener: IFirebaseAuthListener
    ) {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    when {
                        it.isSuccessful -> listener.onSuccess(auth.currentUser)
                        else -> listener.onFailure(it.exception)
                    }
                }
        } catch (e: Exception) {
            listener.onFailure(e)
        }
    }

    fun isUserExists(
        email: String,
        listener: IFirebaseUserExistsListener
    ): Boolean {
        Firebase.auth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
            when {
                it.isSuccessful -> listener.onUserExistsResult(it.result.signInMethods?.isEmpty() == false)
                else -> listener.onFailure()
            }
        }
        return true
    }

    fun isUserLogged() = auth.currentUser != null

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    interface IFirebaseUserExistsListener {
        fun onUserExistsResult(exists: Boolean)
        fun onFailure()
    }

    interface IFirebaseAuthListener {
        fun onSuccess(loggedUser: FirebaseUser?)
        fun onFailure(e: Exception?)
    }

}