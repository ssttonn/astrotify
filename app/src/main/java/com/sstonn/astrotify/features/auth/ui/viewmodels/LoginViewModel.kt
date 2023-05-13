package com.sstonn.astrotify.features.auth.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.sstonn.astrotify.common.MainResult
import com.sstonn.astrotify.common.succeeded
import com.sstonn.astrotify.features.auth.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            val result = authRepository.signInWithGoogle(idToken)
            if (result is MainResult.Success<FirebaseUser>){
                val user = result.data
                Log.d("LoginViewModel", "signInWithGoogle: ${user?.displayName}")
            }
        }
    }

    fun signInWithFacebook(accessToken: String) {
        viewModelScope.launch {
            val result = authRepository.signInWithFacebook(accessToken)
            if (result is MainResult.Success<FirebaseUser>){
                val user = result.data
                Log.d("LoginViewModel", "signInWithFacebook: ${user?.displayName}")
            }
        }
    }
}