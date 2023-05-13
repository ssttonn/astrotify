package com.sstonn.astrotify.features.auth.ui.fragments

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.sstonn.astrotify.R
import com.sstonn.astrotify.databinding.FragmentOnboardBinding
import com.sstonn.astrotify.features.auth.ui.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardFragment : Fragment() {
    private lateinit var binding: FragmentOnboardBinding

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var signInClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInClient = Identity.getSignInClient(requireContext())
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.default_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()



        binding.loginWithGoogleBtn.setOnClickListener {
            signInClient.beginSignIn(signInRequest)
                .addOnSuccessListener(requireActivity()) { result ->
                    try {
                        val intentSenderRequest =
                            IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                        resultLauncher.launch(intentSenderRequest)
                    } catch (e: IntentSender.SendIntentException) {
                    }
                }
                .addOnFailureListener(requireActivity()) { e ->

                }
        }

        binding.loginWithFacebookBtn.setOnClickListener {
            binding.facebookNativeLoginBtn.performClick()
        }

        binding.facebookNativeLoginBtn.setPermissions("email", "public_profile")

        binding.facebookNativeLoginBtn.registerCallback(
            callbackManager, object : FacebookCallback<LoginResult>{
                override fun onCancel() {
                    Log.i("OnboardFragment#Facebook", "onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.i("OnboardFragment#Facebook", "onError: ${error.message}")
                }

                override fun onSuccess(result: LoginResult) {
                    Log.i("OnboardFragment#Facebook", "onSuccess: ${result.accessToken.token}")
                }
            })
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val credential = signInClient.getSignInCredentialFromIntent(data)
                val idToken = credential.googleIdToken
                idToken?.let {
                    viewModel.signInWithGoogle(it)
                }
            } else {
                // Handle failure
            }
        }



}