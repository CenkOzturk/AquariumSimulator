package com.kroq.myaquariumsimulator.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.createAquarium

object AquariumManager {

    var currentType by mutableStateOf(AquariumType.SMALL)
        private set

    var currentAquarium by mutableStateOf<AquariumModel?>(null)
        private set

    fun init(screenWidth: Float, screenHeight: Float) {
        currentAquarium = createAquarium(
            currentType,
            screenWidth,
            screenHeight
        )
    }

    fun upgrade(
        type: AquariumType,
        screenWidth: Float,
        screenHeight: Float
    ) {
        currentType = type

        currentAquarium = createAquarium(
            type,
            screenWidth,
            screenHeight
        )
    }
}