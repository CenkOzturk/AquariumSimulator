package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.createAquarium
import com.kroq.myaquariumsimulator.model.aquarium.toShopItem

object AquariumManager {

    var currentAquarium by mutableStateOf(
        createAquarium(AquariumType.SMALL)
    )
        private set

    fun initialize(aquariumType: String) {
        currentAquarium =
            createAquarium(AquariumType.valueOf(aquariumType))
    }

    fun upgrade(type: AquariumType) {
        val price = type.toShopItem().price

        val success = CoinManager.spendCoins(price)
        if (!success) return

        GameManager.update { it.copy(aquariumType = type.name) }

        refresh()
    }

    private fun refresh() {
        currentAquarium =
            createAquarium(
                AquariumType.valueOf(
                    GameManager.state.aquariumType
                )
            )
    }
}
