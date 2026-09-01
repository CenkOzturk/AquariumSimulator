package com.kukurodev.mykukuroaquarium.ui.aquarium

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.data.Constants.FEED_DURATION
import com.kukurodev.mykukuroaquarium.managers.CoinManager
import com.kukurodev.mykukuroaquarium.managers.DirtManager
import com.kukurodev.mykukuroaquarium.managers.FishFoodManager
import com.kukurodev.mykukuroaquarium.managers.FishManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.managers.UpgradeManager
import com.kukurodev.mykukuroaquarium.model.fish.isFed
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeType
import com.kukurodev.mykukuroaquarium.utils.Utils

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