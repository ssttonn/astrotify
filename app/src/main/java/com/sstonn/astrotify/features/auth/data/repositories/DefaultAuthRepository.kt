package com.sstonn.astrotify.features.auth.data.repositories

import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.sstonn.astrotify.common.MainResult
import com.sstonn.astrotify.features.auth.data.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class DefaultAuthRepository @Inject constructor(private var firebaseAuth: FirebaseAuth, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): AuthRepository {

    override suspend fun signInWithGoogle(idToken: String): MainResult<FirebaseUser> {
        return try {
            val googleSignInCredential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth.signInWithCredential(googleSignInCredential).await()
            if (authResult.user == null){
                MainResult.Error(Exception("Can't find user"))
            }
            MainResult.Success(authResult.user!!)
        }catch (e: java.lang.Exception){
            MainResult.Error(e)
        }
    }

    override suspend fun signInWithFacebook(accessToken: String): MainResult<FirebaseUser> {
        return try {
            val facebookSignInCredential = FacebookAuthProvider.getCredential(accessToken)
            val authResult = firebaseAuth.signInWithCredential(facebookSignInCredential).await()
            if (authResult.user == null){
                MainResult.Error(Exception("Can't find user"))
            }
            MainResult.Success(authResult.user!!)
        }catch (e: java.lang.Exception){
            MainResult.Error(e)
        }
    }

}

