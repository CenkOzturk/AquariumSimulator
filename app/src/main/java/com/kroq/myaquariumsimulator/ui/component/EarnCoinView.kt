package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.game.CoinManager

@Composable
fun EarnCoinView() {
    Box(modifier = Modifier.fillMaxSize()) {

        CoinManager.texts.forEach { t ->

            Text(
                text = t.text,
                color = Color.Yellow.copy(alpha = t.alpha),
                modifier = Modifier
                    .offset(t.x.dp, t.y.dp)
            )
        }
    }
}