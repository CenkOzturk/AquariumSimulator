package com.kukurodev.mykukuroaquarium

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kukurodev.mykukuroaquarium.managers.LanguageManager
import com.kukurodev.mykukuroaquarium.notifications.NotificationHelper
import com.kukurodev.mykukuroaquarium.ui.navigaion.Navigation
import com.kukurodev.mykukuroaquarium.ui.theme.MyAquariumSimulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        LanguageManager.init(this)
        NotificationHelper.createNotificationChannel(this)
        requestNotificationPermission()
        setContent {
            MyAquariumSimulatorTheme {
                Navigation()
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }
    }
}
