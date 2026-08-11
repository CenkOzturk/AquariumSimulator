package com.kroq.myaquariumsimulator.ui.aquarium

import androidx.compose.runtime.Composable
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.data.Constants.FEED_DURATION
import com.kroq.myaquariumsimulator.managers.DirtManager
import com.kroq.myaquariumsimulator.managers.FishFoodManager
import com.kroq.myaquariumsimulator.managers.FishManager
import com.kroq.myaquariumsimulator.managers.GameManager
import com.kroq.myaquariumsimulator.managers.TutorialManager
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.fish.isFed
import com.kroq.myaquariumsimulator.utils.Utils

@Composable
fun FishLayer() {
    FishManager.fishes.forEach { fish ->
        val remaining = (fish.fedUntil - System.currentTimeMillis()).coerceAtLeast(0)
        val progress = (remaining.toFloat() / FEED_DURATION).coerceIn(0f, 1f)

        FishView(fish, fish.isFed(), progress, {
            if (!fish.isFed()) {
                if (GameManager.state.foodCount <= fish.income) {
                    Utils.showToast(R.string.no_food_error, fish.income)
                    return@FishView
                }
                if (!GameManager.state.tutorialCompleted) {
                    TutorialManager.onFeedFish()
                }

                DirtManager.addParticle(x = fish.move.x, y = fish.move.y)
                GameManager.update {
                    it.copy(
                        dirtParticleCount = DirtManager.particles.count()
                    )
                }
                FishFoodManager.feedFish(fish.id)
            }
        })
    }
}