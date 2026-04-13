package com.kroq.myaquariumsimulator.game

import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.createAquarium

object AquariumManager {
    var currentAquarium: AquariumModel? = null

    fun init(screenWidth: Float, screenHeight: Float) {
        currentAquarium = createAquarium(
            AquariumType.SMALL,
            screenWidth,
            screenHeight)
    }
}