package com.kukurodev.mykukuroaquarium.ui.navigaion

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kukurodev.mykukuroaquarium.managers.AudioManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.managers.LanguageManager
import com.kukurodev.mykukuroaquarium.ui.screen.CreditsScreen
import com.kukurodev.mykukuroaquarium.ui.screen.GameScreen
import com.kukurodev.mykukuroaquarium.ui.screen.MainMenuScreen
import com.kukurodev.mykukuroaquarium.ui.screen.SettingsScreen
import com.kukurodev.mykukuroaquarium.ui.screen.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val activity = LocalContext.current as Activity

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen {
                navController.navigate(Screen.MainMenu.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(Screen.MainMenu.route) {
            LaunchedEffect(Unit) {
                AudioManager.playMusic()
            }

            MainMenuScreen(
                onPlay = {
                    navController.navigate(Screen.Game.route)
                },

                onSettings = {
                    navController.navigate(Screen.Settings.route)
                },

                onCredits = {
                    navController.navigate(Screen.Credits.route)
                }
            )
        }

        composable(Screen.Credits.route) {
            CreditsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                music = true,
                soundEffects = true,
                language = LanguageManager.currentLanguage,

                onMusicChanged = { enabled ->
                    AudioManager.setMusicEnabled(enabled)
                },

                onSoundEffectsChanged = { enabled ->
                    AudioManager.setSoundEffectsEnabled(enabled)
                },
                onLanguageChanged = { language ->
                    LanguageManager.setLanguage(
                        context = activity,
                        language = language
                    )
                },
                onResetProgress = {
                    GameManager.resetGame()
                },
                onPrivacyPolicy = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://cenkozturk.github.io/AquariumSimulator/privacy-policy.html")
                    )
                    activity.startActivity(intent)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Game.route) {
            GameScreen()
        }
    }
}