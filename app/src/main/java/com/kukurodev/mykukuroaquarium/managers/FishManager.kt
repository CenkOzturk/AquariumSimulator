package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.mutableStateListOf
import com.kukurodev.mykukuroaquarium.data.Constants.FISH_SIZE
import com.kukurodev.mykukuroaquarium.model.GameProgress
import com.kukurodev.mykukuroaquarium.model.GameState
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumModel
import com.kukurodev.mykukuroaquarium.model.fish.FishDatabase
import com.kukurodev.mykukuroaquarium.model.fish.FishDatabase.getAllFishes
import com.kukurodev.mykukuroaquarium.model.fish.FishModel
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab
import com.kukurodev.mykukuroaquarium.model.task.DailyTaskType
import com.kukurodev.mykukuroaquarium.utils.Utils.random

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
        if(fishId == getAllFishes().first().id) {
            TutorialManager.onFirstFishBought()
        }
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
