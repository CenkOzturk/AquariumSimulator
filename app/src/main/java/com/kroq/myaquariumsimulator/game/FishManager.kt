package com.kroq.myaquariumsimulator.game

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.data.Constants.FISH_SIZE
import com.kroq.myaquariumsimulator.data.Constants.FEED_DURATION
import com.kroq.myaquariumsimulator.model.GameProgress
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.model.fish.FishModel
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.model.task.DailyTaskType
import com.kroq.myaquariumsimulator.utils.Utils.random

object FishManager {
    val fishes = mutableStateListOf<FishModel>()

    fun fishMove(aquarium: AquariumModel) {
        for (i in fishes.indices) {
            fishes[i] = FishLogic.update(fishes[i], aquarium)
        }
    }

    fun buy(fishId: Int, price: Int) {
        CoinManager.purchaseItem(
            ShopTab.FISH,
            price,
            fishId,
            onSuccess = {
                DailyTaskManager.addProgress(DailyTaskType.BUY_FISH)
            }
        )
        syncWithGameState(GameManager.state)
    }

    private fun syncWithGameState(state: GameState) {
        val currentIds = fishes.map { it.id }.toSet()
        val targetIds = state.ownedFishIds

        val newFishIds = targetIds - currentIds
        val aquarium = AquariumManager.currentAquarium

        val newFishes = FishDatabase.getAllFishes()
            .filter { it.id in newFishIds }
            .map { template ->
                template.copy(
                    move = template.move.copy(
                        x = (0f..(aquarium.width - FISH_SIZE)).random(),
                        y = (50f..(aquarium.height - 50f)).random(),
                        targetX = (0f..(aquarium.width - FISH_SIZE)).random(),
                        targetY = (50f..(aquarium.height - 50f)).random()
                    )
                )
            }

        fishes.addAll(newFishes)
    }

    fun updateFish(fishId: Int) {
        GameManager.update { it.copy(ownedFishIds = it.ownedFishIds + fishId) }
    }

    fun canUnlock(fish: FishModel, progress: GameProgress): Boolean {
        return fish.requirementType.isSatisfied(progress)
    }
}
