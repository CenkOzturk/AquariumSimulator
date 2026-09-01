package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.mutableStateListOf
import com.kukurodev.mykukuroaquarium.data.Constants.BUBBLE_SPAWN_TIME
import com.kukurodev.mykukuroaquarium.data.Constants.BUBBLE_VALUE
import com.kukurodev.mykukuroaquarium.model.SoundEffect
import com.kukurodev.mykukuroaquarium.model.extras.BubbleModel
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumModel
import com.kukurodev.mykukuroaquarium.model.task.DailyTaskType
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeType
import com.kukurodev.mykukuroaquarium.utils.Utils.random

object BubbleManager {
    val bubbles = mutableStateListOf<BubbleModel>()

    private var lastSpawnTime = 0L

    fun update(aquarium: AquariumModel) {
        val now = System.currentTimeMillis()
        val upgradeTime = UpgradeManager.getUpgradeValue(UpgradeType.BUBBLE_TIME) * 1000L
        val bubbleTime = BUBBLE_SPAWN_TIME - upgradeTime
        if ((now - lastSpawnTime) > bubbleTime) {
            lastSpawnTime = now
            bubbles.add(
                BubbleModel(
                    id = now,
                    x = (50f..(aquarium.width - 50f)).random(),
                    y = aquarium.height - 20f,
                    radius = (10f..15f).random(), // büyük baloncuk
                    speed = (1.5f..2.5f).random()
                )
            )
        }

        // 🫧 hareket + yukarı çıkınca sil
        val newList = bubbles.mapNotNull { b ->
            val newY = b.y - b.speed
            if (newY < 0) {
                null
            } else {
                b.copy(y = newY)
            }
        }

        bubbles.clear()
        bubbles.addAll(newList)
    }

    fun popBubble(bubbleId: Long) {
        bubbles.removeAll { it.id == bubbleId }
        DailyTaskManager.addProgress(DailyTaskType.POP_BUBBLE)
        CoinManager.addCoins(BUBBLE_VALUE + UpgradeManager.getUpgradeValue(UpgradeType.BUBBLE_VALUE))
        AudioManager.playEffect(SoundEffect.BUBBLE_POP)
    }
}