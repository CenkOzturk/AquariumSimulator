package com.kroq.myaquariumsimulator.game

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.fish.FishModel
import com.kroq.myaquariumsimulator.model.fish.FishDatabase

object FishManager {

    val fishes = mutableStateListOf<FishModel>()

    fun initFromGameState(state: GameState) {

        val initialFishes = FishDatabase.getFishByIds(state.ownedFishIds)

        fishes.clear()
        fishes.addAll(initialFishes)
    }

    fun update(
        aquariumWidth: Float,
        aquariumHeight: Float) {
        for (i in fishes.indices) {
            fishes[i] = FishLogic.update(fishes[i], aquariumWidth, aquariumHeight)
        }
    }

    fun setVisible(id: Int, value: Boolean) {
        val index = fishes.indexOfFirst { it.id == id }
        if (index != -1) {
            fishes[index] = fishes[index].copy(visible = value)
        }
    }
}
