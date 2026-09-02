package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumType
import com.kukurodev.mykukuroaquarium.model.aquarium.createAquarium
import com.kukurodev.mykukuroaquarium.model.aquarium.toShopItem

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

    fun currentAquariumBottom(): Float {
        return if (GameManager.state.ownedItemIds.count() == 0)
            currentAquarium.height - 40f
        else
            currentAquarium.height - 72f
    }
}
