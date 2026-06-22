package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.runtime.Composable
import com.kroq.myaquariumsimulator.data.Constants.FEED_DURATION
import com.kroq.myaquariumsimulator.game.FishFoodManager
import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.game.GameManager
import com.kroq.myaquariumsimulator.model.fish.isFed

@Composable
fun FishLayer() {
    FishManager.fishes.forEach { fish ->
        val remaining = (fish.fedUntil - System.currentTimeMillis()).coerceAtLeast(0)
        val progress = (remaining.toFloat() / FEED_DURATION).coerceIn(0f, 1f)

        FishView(fish, fish.isFed(), progress, {
            if (fish.isFed() || GameManager.state.foodCount > 0) {
                FishManager.feedFish(fish.id)
                FishFoodManager.consumeFood()
            }
        })
    }
}