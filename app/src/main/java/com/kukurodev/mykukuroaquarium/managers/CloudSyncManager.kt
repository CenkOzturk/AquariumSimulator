package com.kukurodev.mykukuroaquarium.managers

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kukurodev.mykukuroaquarium.model.GameState
import com.kukurodev.mykukuroaquarium.model.loadGameState
import com.kukurodev.mykukuroaquarium.model.saveGameState
import com.kukurodev.mykukuroaquarium.utils.Utils.fromJson
import com.kukurodev.mykukuroaquarium.utils.Utils.toJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object CloudSyncManager {
    private const val TAG = "CloudSyncManager"
    private lateinit var appContext: Context
    private val encryptedPrefs by lazy {
        try {
            @Suppress("DEPRECATION")
            val masterKey = MasterKey.Builder(appContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                .build()
            @Suppress("DEPRECATION")
            EncryptedSharedPreferences.create(
                appContext,
                "secure_game_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing EncryptedSharedPreferences", e)
            null
        }
    }

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun isFirebaseAvailable(): Boolean {
        return try {
            FirebaseApp.getApps(appContext).isNotEmpty() || FirebaseApp.initializeApp(appContext) != null
        } catch (e: Exception) {
            false
        }
    }

    fun saveKeystoreBackup(state: GameState) {
        try {
            val json = state.toJson()
            encryptedPrefs?.edit()?.putString("game_state_backup", json)?.apply()
        } catch (e: Exception) {
            Log.e(TAG, "Error saving keystore backup", e)
        }
    }

    fun loadKeystoreBackup(): GameState? {
        try {
            val json = encryptedPrefs?.getString("game_state_backup", null)
            if (!json.isNullOrEmpty()) {
                return json.fromJson<GameState>()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error loading keystore backup", e)
        }
        return null
    }

    suspend fun syncWithCloud(localState: GameState): GameState = suspendCancellableCoroutine { continuation ->
        if (!isFirebaseAvailable()) {
            continuation.resume(localState)
            return@suspendCancellableCoroutine
        }

        val user = try {
            FirebaseAuth.getInstance().currentUser
        } catch (e: Exception) {
            null
        }

        if (user == null) {
            continuation.resume(localState)
            return@suspendCancellableCoroutine
        }
        val uid = user.uid
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(uid).collection("game").document("save")

        docRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val remoteJson = snapshot.getString("state_json")
                val remoteState = remoteJson?.fromJson<GameState>()

                if (remoteState != null) {
                    val shouldUseGoogleData = (remoteState.lastLoginTime != localState.lastLoginTime) &&
                            (localState.coins == 25) &&
                            (!localState.tutorialCompleted)

                    val useRemote = shouldUseGoogleData || (remoteState.lastLoginTime > localState.lastLoginTime)

                    Log.d(TAG, "syncWithCloud -> shouldUseGoogleData: $shouldUseGoogleData, useRemote: $useRemote")

                    if (useRemote) {
                        Log.d(TAG, "Restoring save from Google Cloud.")
                        saveKeystoreBackup(remoteState)
                        continuation.resume(remoteState)
                    } else {
                        Log.d(TAG, "Local save is newer or active. Updating remote Google Cloud.")
                        pushToCloudAsync(localState)
                        continuation.resume(localState)
                    }
                } else {
                    pushToCloudAsync(localState)
                    continuation.resume(localState)
                }
            } else {
                Log.d(TAG, "No remote save exists in Google Cloud. Uploading local save.")
                pushToCloudAsync(localState)
                continuation.resume(localState)
            }
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error fetching cloud save", e)
            continuation.resume(localState)
        }
    }

    fun syncAndLog(context: Context, onResult: ((GameState?, GameState?, Boolean, Boolean) -> Unit)? = null) {
        init(context)
        if (!isFirebaseAvailable()) {
            Log.d("GoogleSyncTest", "Firebase not available for syncAndLog.")
            return
        }
        val currentUser = try {
            FirebaseAuth.getInstance().currentUser
        } catch (e: Exception) {
            null
        }
        if (currentUser == null) {
            Log.d("GoogleSyncTest", "User not signed in with Google, skipping syncAndLog.")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("GoogleSyncTest", "Starting syncAndLog block...")

                val localState = loadGameState(context)
                val keystoreBackup = loadKeystoreBackup() ?: localState
                val keystoreTutorialCompleted = keystoreBackup.tutorialCompleted
                val keystoreCoins = keystoreBackup.coins

                Log.d("GoogleSyncTest", "Local/Keystore state loaded -> coins: $keystoreCoins, tutorialCompleted: $keystoreTutorialCompleted, lastLoginTime: ${keystoreBackup.lastLoginTime}")

                val remoteState = fetchFromCloud()
                val remoteJson = remoteState?.toJson() ?: "null"
                Log.d("GoogleSyncTest", "Fetched from Firebase JSON: $remoteJson")

                if (remoteState != null) {
                    val shouldUseGoogleData = (remoteState.lastLoginTime != keystoreBackup.lastLoginTime) &&
                            (keystoreBackup.coins == 25) &&
                            (!keystoreBackup.tutorialCompleted)

                    Log.d("GoogleSyncTest", "Rule check -> remoteLastLogin (${remoteState.lastLoginTime}) != localLastLogin (${keystoreBackup.lastLoginTime}) && coins == 25 && tutorialCompleted == false => shouldUseGoogleData: $shouldUseGoogleData")

                    val finalState = if (shouldUseGoogleData) {
                        Log.d("GoogleSyncTest", "Restoring Google Cloud data!")
                        saveKeystoreBackup(remoteState)
                        saveGameState(context, remoteState)
                        GameManager.initialize(remoteState)
                        remoteState
                    } else {
                        Log.d("GoogleSyncTest", "Keeping local/keystore data and pushing to Google Cloud!")
                        pushToCloud(keystoreBackup)
                        keystoreBackup
                    }

                    val firebaseTutorialCompleted = finalState.tutorialCompleted
                    val firebaseCoins = finalState.coins
                    val isTutorialMatch = keystoreTutorialCompleted == firebaseTutorialCompleted
                    val isCoinsMatch = keystoreCoins == firebaseCoins

                    Log.d(
                        "GoogleSyncTest",
                        "Comparison -> Keystore (tutorial: $keystoreTutorialCompleted, coins: $keystoreCoins) | Firebase (tutorial: ${remoteState.tutorialCompleted}, coins: ${remoteState.coins}) | Used: ${if (shouldUseGoogleData) "Google" else "Keystore"}"
                    )

                    onResult?.invoke(keystoreBackup, remoteState, isTutorialMatch, isCoinsMatch)
                } else {
                    Log.d("GoogleSyncTest", "No remote state found in Firebase. Pushing keystore backup to cloud...")
                    pushToCloud(keystoreBackup)
                }
            } catch (e: Exception) {
                Log.e("GoogleSyncTest", "Exception in syncAndLog block", e)
            }
        }
    }

    suspend fun pushToCloud(state: GameState): Boolean = suspendCancellableCoroutine { continuation ->
        if (!isFirebaseAvailable()) {
            Log.d("GoogleSyncTest", "pushToCloud: Firebase not available.")
            continuation.resume(false)
            return@suspendCancellableCoroutine
        }
        val user = try {
            FirebaseAuth.getInstance().currentUser
        } catch (e: Exception) {
            null
        } ?: run {
            Log.d("GoogleSyncTest", "pushToCloud: currentUser is null.")
            continuation.resume(false)
            return@suspendCancellableCoroutine
        }
        val uid = user.uid
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(uid).collection("game").document("save")

        val data = mapOf(
            "state_json" to state.toJson(),
            "last_login_time" to state.lastLoginTime,
            "updated_at" to Timestamp.now()
        )

        Log.d("GoogleSyncTest", "Calling docRef.set in pushToCloud for uid: $uid")
        docRef.set(data)
            .addOnSuccessListener {
                Log.d("GoogleSyncTest", "pushToCloud onSuccess called.")
                continuation.resume(true)
            }
            .addOnFailureListener { e ->
                Log.e("GoogleSyncTest", "pushToCloud onFailure called", e)
                continuation.resume(false)
            }
    }

    private fun pushToCloudAsync(state: GameState) {
        CoroutineScope(Dispatchers.IO).launch {
            pushToCloud(state)
        }
    }

    suspend fun fetchFromCloud(): GameState? = suspendCancellableCoroutine { continuation ->
        if (!isFirebaseAvailable()) {
            Log.d("GoogleSyncTest", "fetchFromCloud: Firebase not available.")
            continuation.resume(null)
            return@suspendCancellableCoroutine
        }
        val user = try {
            FirebaseAuth.getInstance().currentUser
        } catch (e: Exception) {
            null
        }
        if (user == null) {
            Log.d("GoogleSyncTest", "fetchFromCloud: currentUser is null.")
            continuation.resume(null)
            return@suspendCancellableCoroutine
        }
        val uid = user.uid
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(uid).collection("game").document("save")

        Log.d("GoogleSyncTest", "Calling docRef.get in fetchFromCloud for uid: $uid")
        docRef.get()
            .addOnSuccessListener { snapshot ->
                Log.d("GoogleSyncTest", "fetchFromCloud onSuccess called. exists: ${snapshot.exists()}")
                if (snapshot.exists()) {
                    val remoteJson = snapshot.getString("state_json")
                    val remoteState = remoteJson?.fromJson<GameState>()
                    continuation.resume(remoteState)
                } else {
                    continuation.resume(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("GoogleSyncTest", "fetchFromCloud onFailure called", e)
                continuation.resume(null)
            }
    }

    fun deleteCloudSave(onComplete: (() -> Unit)? = null) {
        if (!isFirebaseAvailable()) {
            onComplete?.invoke()
            return
        }
        val user = try {
            FirebaseAuth.getInstance().currentUser
        } catch (e: Exception) {
            null
        } ?: run {
            onComplete?.invoke()
            return
        }
        val uid = user.uid
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(uid).collection("game").document("save")

        docRef.delete().addOnSuccessListener {
            Log.d(TAG, "Cloud save deleted successfully from Google Firestore.")
            onComplete?.invoke()
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error deleting cloud save from Google Firestore", e)
            onComplete?.invoke()
        }
    }
}
