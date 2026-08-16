package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.ui.component.buttons.GameMenuButton

@Composable
fun MainMenuScreen(
    onPlay: () -> Unit,
    onSettings: () -> Unit,
    onCredits: () -> Unit
) {
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
                gradient = GameColors.WelcomeGift,
                onClick = onSettings
            )

            Spacer(
                Modifier.height(16.dp)
            )

            GameMenuButton(
                text = stringResource(R.string.menu_credits),
                gradient = GameColors.Shop,
                onClick = onCredits
            )

            Spacer(
                Modifier.weight(1f)
            )

            Text(
                text = "Version 1.0.0",
                fontSize = 14.sp,
                color = Color.White.copy(.75f)
            )

            Spacer(
                Modifier.height(24.dp)
            )
        }
    }
}