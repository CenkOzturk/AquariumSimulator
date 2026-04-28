package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.utils.Utils.random

object BubbleManager {
    val bubbles = mutableStateListOf<BubbleModel>()

    private var lastSpawnTime = 0L

    fun update(
        aquariumWidth: Float,
        aquariumHeight: Float
    ) {

        val now = System.currentTimeMillis()

        // ⏱️ 60 sn'de bir spawn (istersen 20-30 sn yap)
        if (now - lastSpawnTime > 60_000) {

            lastSpawnTime = now

            bubbles.add(
                BubbleModel(
                    id = now,
                    x = (50f..(aquariumWidth - 50f)).random(),
                    y = aquariumHeight - 20f,
                    radius = (10f..15f).random(), // büyük baloncuk
                    speed = (1.5f..2.5f).random()
                )
            )
        }

        // 🫧 hareket + yukarı çıkınca sil
        for (i in bubbles.indices) {

            val b = bubbles[i]

            val newY = b.y - b.speed

            if (newY < 0) {
                bubbles.removeAt(i)
                break
            } else {
                bubbles[i] = b.copy(y = newY)
            }
        }
    }

    fun popBubble(context: Context, bubble: BubbleModel) {
        bubbles.remove(bubble)

        GameManager.update(context) {
            it.copy(coins = it.coins + 5)
        }
    }

    data class BubbleModel(
        val id: Long,
        val x: Float,
        val y: Float,
        val radius: Float,
        val speed: Float
    )
}