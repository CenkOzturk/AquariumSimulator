package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.mutableStateListOf
import com.kukurodev.mykukuroaquarium.model.extras.DirtParticleModel
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumModel
import com.kukurodev.mykukuroaquarium.utils.Utils.random
import kotlin.math.min
import kotlin.random.Random

object DirtManager {
    val particles = mutableStateListOf<DirtParticleModel>()

    fun initialize(aquarium: AquariumModel) {
        particles.clear()
        if (GameManager.state.dirtParticleCount != 0) {
            for (i in 1..GameManager.state.dirtParticleCount) {
                addParticle((8f..150f).random(), aquarium.height - 8f)
            }
        }
    }

    fun addParticle(x: Float, y: Float) {
        particles += DirtParticleModel(
            x = x,
            y = y,
            speed = Random.nextFloat() * 0.8f + 0.4f
        )
    }

    fun update(aquarium: AquariumModel) {
        val targetY =
            if (GameManager.state.ownedItemIds.count() == 0) aquarium.height - 8f
            else aquarium.height - 32f

        particles.replaceAll { particle ->
            if (particle.y >= targetY) {
                particle
            } else {
                particle.copy(
                    y = min(
                        particle.y + particle.speed,
                        targetY
                    )
                )
            }
        }
    }

    fun needCleaning(): Boolean {
        return particles.isNotEmpty()
    }

    fun clear() {
        particles.clear()
    }
}