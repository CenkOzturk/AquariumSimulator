package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.data.Constants.BUBBLE_SPAWN_TIME
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.utils.Utils.random

object BubbleManager {
    val bubbles = mutableStateListOf<BubbleModel>()

    private var lastSpawnTime = 0L

    fun update(aquarium: AquariumModel) {
        val now = System.currentTimeMillis()

        if (now - lastSpawnTime > BUBBLE_SPAWN_TIME) {

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

    fun popBubble(context: Context, bubbleId: Long) {

        bubbles.removeAll { it.id == bubbleId }

        CoinManager.addCoins(context, 5000)
    }

    data class BubbleModel(
        val id: Long,
        val x: Float,
        val y: Float,
        val radius: Float,
        val speed: Float
    )
}