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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.ui.aquarium.BubbleView
import com.kroq.myaquariumsimulator.ui.component.CreditItem
import com.kroq.myaquariumsimulator.ui.component.buttons.GameMenuButton


@Composable
fun CreditsScreen(
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
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(48.dp))

            Text(
                text = stringResource(R.string.credits_title),
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(40.dp))

            CreditItem(
                stringResource(R.string.credits_game_design),
                stringResource(R.string.credits_name_cenk)
            )

            CreditItem(
                stringResource(R.string.credits_programming),
                stringResource(R.string.credits_name_cenk)
            )

            CreditItem(
                stringResource(R.string.credits_game_art),
                stringResource(R.string.credits_name_cenk_chatgpt)
            )

            CreditItem(
                stringResource(R.string.credits_music),
                stringResource(R.string.credits_coming_soon)
            )

            CreditItem(
                stringResource(R.string.credits_version),
                "1.0.0"
            )

            Spacer(Modifier.weight(1f))

            GameMenuButton(
                text = stringResource(R.string.menu_back),
                gradient = GameColors.Shop,
                onClick = onBack
            )

            Spacer(Modifier.height(28.dp))
        }
    }
}