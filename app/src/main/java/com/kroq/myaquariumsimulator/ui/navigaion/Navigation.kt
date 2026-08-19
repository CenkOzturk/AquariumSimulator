package com.kroq.myaquariumsimulator.ui.navigaion

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kroq.myaquariumsimulator.managers.AudioManager
import com.kroq.myaquariumsimulator.managers.GameManager
import com.kroq.myaquariumsimulator.managers.LanguageManager
import com.kroq.myaquariumsimulator.ui.screen.CreditsScreen
import com.kroq.myaquariumsimulator.ui.screen.GameScreen
import com.kroq.myaquariumsimulator.ui.screen.MainMenuScreen
import com.kroq.myaquariumsimulator.ui.screen.SettingsScreen
import com.kroq.myaquariumsimulator.ui.screen.SplashScreen

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
                onPrivacyPolicy = {},
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