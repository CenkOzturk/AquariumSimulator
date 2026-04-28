package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.data.Constants.FISH_SIZE
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.fish.FishModel
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import com.kroq.myaquariumsimulator.utils.Utils
import com.kroq.myaquariumsimulator.utils.Utils.random

object FishManager {

    val fishes = mutableStateListOf<FishModel>()

    fun initFromGameState(state: GameState) {

        val initialFishes = FishDatabase.getFishByIds(state.ownedFishIds)

        fishes.clear()
        fishes.addAll(initialFishes)
    }

    fun fishMove(
        aquariumWidth: Float,
        aquariumHeight: Float) {
        for (i in fishes.indices) {
            fishes[i] = FishLogic.update(fishes[i], aquariumWidth, aquariumHeight)
        }
    }

    fun buy(context: Context, fishId: Int) {

        val fixsh = FishDatabase.getAllFishes()
            .find { it.id == fishId } ?: return

        val price = 2 // şimdilik sabit

        val success = CoinManager.spendCoins(context, price)

        if (!success) {
            Utils.showToast("Satın alma başarısız! Paran yetmedi :(")
            return
        }

        GameManager.update(context) {
            it.copy(
                ownedFishIds = it.ownedFishIds + fishId
            )
        }

        syncWithGameState(GameManager.state)
    }

    fun syncWithGameState(state: GameState) {
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
}
