package com.kukurodev.mykukuroaquarium.ui.screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.kukurodev.mykukuroaquarium.BuildConfig
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.AudioManager
import com.kukurodev.mykukuroaquarium.managers.AuthManager
import com.kukurodev.mykukuroaquarium.managers.CloudSyncManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.loadGameState
import com.kukurodev.mykukuroaquarium.ui.component.buttons.GameMenuButton

@Composable
fun MainMenuScreen(
    onPlay: () -> Unit,
    onSettings: () -> Unit,
    onCredits: () -> Unit
) {
    val context = LocalContext.current
    var isLoggedIn by remember { mutableStateOf(false) }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("GoogleSyncTest", "ActivityResult received: resultCode = ${result.resultCode}")
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken
            Log.d("GoogleSyncTest", "Google Sign-In Account obtained. idToken is null? ${idToken == null}")
            if (idToken != null) {
                AuthManager.signInWithGoogleCredential(idToken) { success ->
                    Log.d("GoogleSyncTest", "AuthManager callback result: success = $success")
                    if (success) {
                        isLoggedIn = true
                        CloudSyncManager.syncAndLog(context)
                    }
                }
            }
        } catch (e: ApiException) {
            Log.e("GoogleSyncTest", "GoogleSignIn ApiException: statusCode = ${e.statusCode}, message = ${e.message}", e)
        } catch (e: Exception) {
            Log.e("GoogleSyncTest", "GoogleSignIn general Exception: ${e.message}", e)
        }
    }

    LaunchedEffect(Unit) {
        val loaded = loadGameState(context)
        GameManager.initialize(loaded)
        AudioManager.initialize()
        val user = try { FirebaseAuth.getInstance().currentUser } catch (e: Exception) { null }
        isLoggedIn = user != null
        if (user != null) {
            CloudSyncManager.syncAndLog(context)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Full screen background
        Image(
            painter = painterResource(R.drawable.bg_main_menu),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                Modifier.weight(.7f)
            )

            Spacer(
                Modifier.height(276.dp)
            )

            GameMenuButton(
                text = stringResource(R.string.menu_play),
                gradient = GameColors.Ocean,
                isPrimary = true,
                onClick = onPlay
            )

            Spacer(
                Modifier.height(16.dp)
            )

            GameMenuButton(
                text = stringResource(R.string.menu_settings),
                gradient = GameColors.Purple,
                onClick = onSettings
            )

            Spacer(
                Modifier.height(16.dp)
            )

            GameMenuButton(
                text = stringResource(R.string.menu_credits),
                gradient = GameColors.Sun,
                onClick = onCredits
            )

            Spacer(
                Modifier.weight(1f)
            )

            if (!isLoggedIn) {
                GoogleSignInButton(
                    onClick = {
                        try {
                            val signInClient = AuthManager.getGoogleSignInClient(context)
                            googleSignInLauncher.launch(signInClient.signInIntent)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                )
            } else {
                Text(
                    text = "✓ " + stringResource(R.string.google_connected),
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(
                Modifier.height(12.dp)
            )

            Text(
                text = stringResource(R.string.settings_version) + BuildConfig.VERSION_NAME,
                fontSize = 14.sp,
                color = Color.White.copy(.75f)
            )

            Spacer(
                Modifier.height(24.dp)
            )
        }
    }
}

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(25.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "G",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4285F4)
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.google_sign_in),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3C4043)
            )
        }
    }
}
