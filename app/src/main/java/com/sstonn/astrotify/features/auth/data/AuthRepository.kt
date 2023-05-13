package com.sstonn.astrotify.features.auth.data

import com.google.firebase.auth.FirebaseUser
import com.sstonn.astrotify.common.MainResult

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): MainResult<FirebaseUser>
    suspend fun signInWithFacebook(accessToken: String): MainResult<FirebaseUser>
}