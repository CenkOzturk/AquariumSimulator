package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.ui.aquarium.BubbleView
import com.kroq.myaquariumsimulator.ui.component.buttons.GameMenuButton
import com.kroq.myaquariumsimulator.ui.component.settings.*


@Composable
fun SettingsScreen(
    music: Boolean,
    soundEffects: Boolean,
    notifications: Boolean,
    onMusicChanged: (Boolean) -> Unit,
    onSoundEffectsChanged: (Boolean) -> Unit,
    onNotificationsChanged: (Boolean) -> Unit,
    onResetProgress: () -> Unit,
    onPrivacyPolicy: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF65D5FF),
                        Color(0xFF45C4F4),
                        Color(0xFF2AA7E3),
                        Color(0xFF1286CF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(24.dp))

            Text(
                text = "SETTINGS",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(32.dp))

            SettingsToggleItem(
                title = "Music",
                checked = music,
                onCheckedChange = onMusicChanged
            )

            Spacer(Modifier.height(12.dp))

            SettingsToggleItem(
                title = "Sound Effects",
                checked = soundEffects,
                onCheckedChange = onSoundEffectsChanged
            )

            Spacer(Modifier.height(12.dp))

            SettingsToggleItem(
                title = "Notifications",
                checked = notifications,
                onCheckedChange = onNotificationsChanged
            )

            Spacer(Modifier.height(12.dp))

            SettingsActionItem(
                title = "Reset Progress",
                onClick = onResetProgress
            )

            Spacer(Modifier.height(12.dp))

            SettingsActionItem(
                title = "Privacy Policy",
                onClick = onPrivacyPolicy
            )

            Spacer(Modifier.height(12.dp))

            SettingsVersionItem(
                version = "1.0.0"
            )

            Spacer(Modifier.weight(1f))

            GameMenuButton(
                text = "BACK",
                gradient = GameColors.Shop,
                onClick = onBack
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}