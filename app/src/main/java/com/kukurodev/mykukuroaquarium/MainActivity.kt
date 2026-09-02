package com.kukurodev.mykukuroaquarium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kukurodev.mykukuroaquarium.managers.LanguageManager
import com.kukurodev.mykukuroaquarium.ui.navigaion.Navigation
import com.kukurodev.mykukuroaquarium.ui.theme.MyAquariumSimulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        LanguageManager.init(this)
        setContent {
            MyAquariumSimulatorTheme {
                Navigation()
            }
        }
    }
}