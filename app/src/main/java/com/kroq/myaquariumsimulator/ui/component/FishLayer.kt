package com.kroq.myaquariumsimulator.ui.component

import android.util.Log
import androidx.compose.runtime.Composable
import com.kroq.myaquariumsimulator.data.Constants.FEED_DURATION
import com.kroq.myaquariumsimulator.game.FishManager

@Composable
fun FishLayer() {
    FishManager.fishes.forEach { fish ->
        val remaining = (fish.fedUntil - System.currentTimeMillis()).coerceAtLeast(0)
        val progress = (remaining.toFloat() / FEED_DURATION).coerceIn(0f, 1f)

        FishView(fish, remaining > 0, progress, {
            FishManager.feedFish(fish.id)
        })
    }
}