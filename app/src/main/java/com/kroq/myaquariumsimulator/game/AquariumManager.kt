package com.kroq.myaquariumsimulator.game

import android.content.Context
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.createAquarium
import com.kroq.myaquariumsimulator.model.aquarium.toShopItem

object AquariumManager {

    fun upgrade(
        context: Context,
        type: AquariumType
    ) {

        val price = type.toShopItem().price

        val success = CoinManager.spendCoins(context, price)
        if (!success) return

        GameManager.update(context) {
            it.copy(aquariumType = type.name)
        }
    }

    fun getCurrentAquarium(
        screenWidth: Float,
        screenHeight: Float
    ): AquariumModel {

        val type = AquariumType.valueOf(GameManager.state.aquariumType)

        return createAquarium(type, screenWidth, screenHeight)
    }
}