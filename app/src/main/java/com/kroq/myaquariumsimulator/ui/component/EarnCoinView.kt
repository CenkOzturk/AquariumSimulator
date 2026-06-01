package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.game.CoinManager
import kotlinx.coroutines.launch

@Composable
fun EarnCoinView(
    coin: CoinManager.FloatingText
) {
    val offsetY = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(coin.id) {
        launch {
            offsetY.animateTo(
                targetValue = -60f, // yukarı çıkış
                animationSpec = tween(
                    durationMillis = 900
                )
            )
        }

        launch {
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(900)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "+${coin.text} 💰",
            color = Color.Yellow.copy(alpha = alpha.value),
            modifier = Modifier
                .offset(
                    x = coin.x.dp,
                    y = (coin.y + offsetY.value).dp
                )
        )
    }
}