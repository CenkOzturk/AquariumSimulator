package com.kroq.myaquariumsimulator.ui.aquarium

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.data.Constants.FEED_DURATION
import com.kroq.myaquariumsimulator.managers.CoinManager
import com.kroq.myaquariumsimulator.managers.DirtManager
import com.kroq.myaquariumsimulator.managers.FishFoodManager
import com.kroq.myaquariumsimulator.managers.FishManager
import com.kroq.myaquariumsimulator.managers.GameManager
import com.kroq.myaquariumsimulator.managers.TutorialManager
import com.kroq.myaquariumsimulator.managers.UpgradeManager
import com.kroq.myaquariumsimulator.model.fish.isFed
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeType
import com.kroq.myaquariumsimulator.utils.Utils

@Composable
fun FishLayer() {
    FishManager.fishes.forEach { fish ->
        val remaining = (fish.fedUntil - System.currentTimeMillis()).coerceAtLeast(0)
        val progress = (remaining.toFloat() / FEED_DURATION).coerceIn(0f, 1f)
        val text: String = stringResource(R.string.no_food_error, fish.income)

        FishView(fish, fish.isFed(), progress, {
            if (!fish.isFed()) {
                if (GameManager.state.foodCount < fish.income) {
                    Utils.showToast(text)
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
                CoinManager.spawnCoin(
                    x = fish.move.x,
                    y = fish.move.y,
                    direction = fish.move.direction,
                    count = fish.income,
                    amount = UpgradeManager
                        .getUpgradeValue(UpgradeType.COIN_VALUE)
                        .coerceAtLeast(1)
                )
            }
        })
    }
}