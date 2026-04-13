package com.kroq.myaquariumsimulator.game

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.fish.FishModel
import com.kroq.myaquariumsimulator.model.fish.FishDatabase

object FishManager {

    val fishes = mutableStateListOf<FishModel>()

    fun init() {
        fishes.clear()
        fishes.addAll(FishDatabase.getInitialFishes())
    }

    fun update(
        aquariumWidth: Float,
        aquariumHeight: Float) {
        for (i in fishes.indices) {
            fishes[i] = FishLogic.update(fishes[i], aquariumWidth, aquariumHeight)
        }
    }

    /*fun setVisible(id: Int, value: Boolean) {
        val index = fishes.indexOfFirst { it.id == id }
        if (index != -1) {
            fishes[index] = fishes[index].copy(visible = value)
        }
    }*/
}
