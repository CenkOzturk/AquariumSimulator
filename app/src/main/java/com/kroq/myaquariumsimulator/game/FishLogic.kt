package com.kroq.myaquariumsimulator.game

import com.kroq.myaquariumsimulator.data.Constants.FISH_SIZE
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.model.fish.FishModel
import com.kroq.myaquariumsimulator.utils.Utils.random

object FishLogic {
    fun update(
        fish: FishModel,
        aquarium: AquariumModel
    ): FishModel {
        val isTargetOutOfBounds =
            fish.move.targetX !in 0f..(aquarium.width - FISH_SIZE) ||
                    fish.move.targetY !in 50f..(aquarium.height - 50f)

        val fixedTargetX = if (isTargetOutOfBounds) {
            (0f..(aquarium.width - FISH_SIZE)).random()
        } else {
            fish.move.targetX
        }

        val fixedTargetY = if (isTargetOutOfBounds) {
            (50f..(aquarium.height - 50f)).random()
        } else {
            fish.move.targetY
        }

        val dx = fixedTargetX - fish.move.x
        val dy = fixedTargetY - fish.move.y

        val distance = kotlin.math.sqrt(dx * dx + dy * dy)

        // 🎯 hedefe ulaştıysa yeni hedef
        if (distance < 15f) {
            return fish.copy(
                move = fish.move.copy(
                    targetX = (0f..(aquarium.width - FISH_SIZE)).random(),
                    targetY = (100f..(aquarium.height - 100f)).random()
                )
            )
        }

        val dirX = dx / distance
        val dirY = dy / distance

        // 🌊 YUMUŞAK DALGA (ASIL OLAY BURASI)
        val time = System.currentTimeMillis() / 400.0

        val waveX = kotlin.math.sin(time + fish.move.phase) * 0.3f
        val waveY = kotlin.math.cos(time + fish.move.phase) * 0.6f

        val newX = fish.move.x + (dirX + waveX).toFloat() * fish.move.speed
        val newY = fish.move.y + (dirY + waveY).toFloat() * fish.move.speed

        return fish.copy(
            move = fish.move.copy(
                x = newX.coerceIn(0f, aquarium.width - FISH_SIZE),
                y = newY.coerceIn(50f, aquarium.height - 50f),
                direction = if (dirX > 0) 1 else -1
            )
        )
    }
}