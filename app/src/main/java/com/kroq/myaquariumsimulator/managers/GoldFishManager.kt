package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kroq.myaquariumsimulator.data.Constants.GOLD_FISH_REWARD
import com.kroq.myaquariumsimulator.data.Constants.GOLD_FISH_SPAWN_INTERVAL
import kotlin.random.Random

object GoldFishManager {
    var visible by mutableStateOf(false)
        private set

    var x by mutableFloatStateOf(0f)
        private set

    var y by mutableFloatStateOf(0f)
        private set

    fun update(
        screenWidth: Float,
        screenHeight: Float
    ) {

        if (!GameManager.state.goldFishUnlocked) {
            visible = false
            return
        }

        if (visible) {
            return
        }

        val now = System.currentTimeMillis()

        val elapsed =
            now - GameManager.state.lastGoldFishTime

        if (elapsed >= GOLD_FISH_SPAWN_INTERVAL) {
            spawn(
                screenWidth,
                screenHeight
            )
        }
    }

    private fun spawn(
        screenWidth: Float,
        screenHeight: Float
    ) {

        visible = true

        x = -180f

        y =
            Random.nextFloat() *
                    (screenHeight * 0.65f) +
                    screenHeight * 0.15f

        // ÖNEMLİ:
        // Balık çıktığı anda zamanı kaydediyoruz.
        GameManager.update {
            it.copy(
                lastGoldFishTime = System.currentTimeMillis()
            )
        }
    }

    fun move(
        screenWidth: Float
    ) {

        if (!visible) return

        x += 1.2f

        if (x > screenWidth + 180f) {
            visible = false
        }
    }

    fun collect() {
        if (!visible) return

        GameManager.update {
            it.copy(
                coins = it.coins + GOLD_FISH_REWARD
            )
        }

        visible = false
    }
}