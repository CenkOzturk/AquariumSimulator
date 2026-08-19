package com.kroq.myaquariumsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kroq.myaquariumsimulator.managers.LanguageManager
import com.kroq.myaquariumsimulator.ui.navigaion.Navigation
import com.kroq.myaquariumsimulator.ui.theme.MyAquariumSimulatorTheme

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