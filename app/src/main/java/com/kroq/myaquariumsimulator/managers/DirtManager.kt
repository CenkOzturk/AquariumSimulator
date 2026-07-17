package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.extras.DirtParticleModel
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import kotlin.math.min
import kotlin.random.Random

object DirtManager {
    val particles = mutableStateListOf<DirtParticleModel>()

    fun addParticle(x: Float, y: Float) {
        particles += DirtParticleModel(
            x = x,
            y = y,
            speed = Random.nextFloat() * 0.8f + 0.4f
        )
    }

    fun update(aquarium: AquariumModel) {
        val targetY = aquarium.height - 32f

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