package com.kukurodev.mykukuroaquarium.managers

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kukurodev.mykukuroaquarium.R

object AuthManager {
    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val resId = context.resources.getIdentifier("default_web_client_id", "string", context.packageName)
        val webClientId = if (resId != 0) {
            context.getString(resId)
        } else {
            "1017641982931.apps.googleusercontent.com"
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    fun signInWithGoogleCredential(idToken: String, onResult: (Boolean) -> Unit) {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("GoogleSyncTest", "Firebase Auth sign-in SUCCESS: ${task.result?.user?.uid}")
                        onResult(true)
                    } else {
                        Log.e("GoogleSyncTest", "Firebase Auth sign-in FAILED", task.exception)
                        onResult(false)
                    }
                }
        } catch (e: Exception) {
            Log.e("GoogleSyncTest", "Exception in signInWithGoogleCredential", e)
            onResult(false)
        }
    }
}
