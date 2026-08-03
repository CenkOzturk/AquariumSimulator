package com.kroq.myaquariumsimulator.ui.navigaion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kroq.myaquariumsimulator.ui.screen.CreditsScreen
import com.kroq.myaquariumsimulator.ui.screen.GameScreen
import com.kroq.myaquariumsimulator.ui.screen.MainMenuScreen
import com.kroq.myaquariumsimulator.ui.screen.SettingsScreen
import com.kroq.myaquariumsimulator.ui.screen.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
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
                soundEffects = false,
                notifications = true,
                onMusicChanged = {},
                onSoundEffectsChanged = {},
                onNotificationsChanged = {},
                onResetProgress = {},
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