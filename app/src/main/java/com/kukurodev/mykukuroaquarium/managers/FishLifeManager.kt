package com.kukurodev.mykukuroaquarium.managers

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kukurodev.mykukuroaquarium.data.Constants.DEATH_GRACE_PERIOD_MS
import com.kukurodev.mykukuroaquarium.model.fish.FishDatabase
import com.kukurodev.mykukuroaquarium.model.fish.FishModel
import com.kukurodev.mykukuroaquarium.notifications.NotificationHelper

object FishLifeManager {
    var hasPendingDeathNotice by mutableStateOf(false)
        private set

    var deadFishes by mutableStateOf<List<FishModel>>(emptyList())
        private set

    fun checkAndProcessDeathOnLaunch(): Boolean {
        val state = GameManager.state
        val allFish = state.activeFishes + state.ownedFishIds
        if (allFish.isEmpty()) return false

        val now = System.currentTimeMillis()
        val deadFishIds = allFish.filter { fishId ->
            val lastFed = state.fishLastFedTimes[fishId] ?: 0L
            lastFed > 0L && (now - lastFed) >= DEATH_GRACE_PERIOD_MS
        }.toSet()

        if (deadFishIds.isNotEmpty()) {
            deadFishes = FishDatabase.getFishByIds(deadFishIds)
            hasPendingDeathNotice = true
            GameManager.update {
                it.copy(
                    activeFishes = it.activeFishes - deadFishIds,
                    ownedFishIds = it.ownedFishIds - deadFishIds,
                    fishLastFedTimes = it.fishLastFedTimes - deadFishIds
                )
            }
            return true
        }
        return false
    }

    fun acknowledgeDeathNotice() {
        hasPendingDeathNotice = false
        deadFishes = emptyList()
    }

    fun onFishFed(context: Context, fishId: Int) {
        val now = System.currentTimeMillis()
        GameManager.update {
            it.copy(
                fishLastFedTimes = it.fishLastFedTimes + (fishId to now)
            )
        }
        NotificationHelper.scheduleNotifications(context, now)
    }
}
