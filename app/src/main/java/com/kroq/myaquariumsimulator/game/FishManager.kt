package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.data.Constants.FISH_SIZE
import com.kroq.myaquariumsimulator.model.GameProgress
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.fish.FishModel
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import com.kroq.myaquariumsimulator.model.fish.toShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.utils.Utils.random

object FishManager {
    val fishes = mutableStateListOf<FishModel>()

    fun fishMove(
        aquariumWidth: Float,
        aquariumHeight: Float) {
        for (i in fishes.indices) {
            fishes[i] = FishLogic.update(fishes[i], aquariumWidth, aquariumHeight)
        }
    }

    fun buy(context: Context, fishId: Int) {
        CoinManager.purchaseItem(
            context,
            ShopTab.FISH,
            FishDatabase.getAllFishes().map { it.toShopItem() }, fishId
        )
        syncWithGameState(GameManager.state)
    }

    private fun syncWithGameState(state: GameState) {
        val currentIds = fishes.map { it.id }.toSet()
        val targetIds = state.ownedFishIds

        val newFishIds = targetIds - currentIds
        val aquarium = AquariumManager.getCurrentAquarium()

        val newFishes = FishDatabase.getAllFishes()
            .filter { it.id in newFishIds }
            .map { template ->
                template.copy(
                    x = (0f..(aquarium.width - FISH_SIZE)).random(),
                    y = (50f..(aquarium.height - 50f)).random(),
                    targetX = (0f..(aquarium.width - FISH_SIZE)).random(),
                    targetY = (50f..(aquarium.height - 50f)).random()
                )
            }

        fishes.addAll(newFishes)
    }

    fun canUnlock(fish: FishModel, progress: GameProgress): Boolean {
        return fish.requirementType.isSatisfied(progress)
    }
}
