package com.kroq.myaquariumsimulator.game

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel

object AquariumManager {

    val aquariums = mutableStateListOf<AquariumModel>()

    fun init(screenWidth: Float, screenHeight: Float) {

        aquariums.clear()

        aquariums.add(
            AquariumModel(
                id = 1,
                offsetX = screenWidth * 0.1f,
                offsetY = screenHeight * 0.15f,
                width = screenWidth * 0.8f,
                height = screenHeight * 0.6f,
                color = 0xFF2F8FCE
            )
        )
    }
}