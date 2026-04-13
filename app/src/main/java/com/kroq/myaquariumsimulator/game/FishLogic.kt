package com.kroq.myaquariumsimulator.game

import com.kroq.myaquariumsimulator.model.fish.FishModel

object FishLogic {

    private const val FISH_SIZE = 70f

    fun update(
        fish: FishModel,
        aquariumWidth: Float,
        aquariumHeight: Float
    ): FishModel {

        val dx = fish.targetX - fish.x
        val dy = fish.targetY - fish.y

        val distance = kotlin.math.sqrt(dx * dx + dy * dy)

        // 🎯 hedefe ulaştıysa yeni hedef
        if (distance < 15f) {
            return fish.copy(
                targetX = (0f..(aquariumWidth - FISH_SIZE)).random(),
                targetY = (100f..(aquariumHeight - 100f)).random()
            )
        }

        val dirX = dx / distance
        val dirY = dy / distance

        // 🌊 YUMUŞAK DALGA (ASIL OLAY BURASI)
        val time = System.currentTimeMillis() / 400.0

        val waveX = kotlin.math.sin(time + fish.phase) * 0.3f
        val waveY = kotlin.math.cos(time + fish.phase) * 0.6f

        val newX = fish.x + (dirX + waveX).toFloat() * fish.speed
        val newY = fish.y + (dirY + waveY).toFloat() * fish.speed

        return fish.copy(
            x = newX.coerceIn(0f, aquariumWidth - FISH_SIZE),
            y = newY.coerceIn(50f, aquariumHeight - 50f),
            direction = if (dirX > 0) 1 else -1
        )
    }

    private fun ClosedFloatingPointRange<Float>.random(): Float {
        return (start + Math.random() * (endInclusive - start)).toFloat()
    }
}