package com.kroq.myaquariumsimulator.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kroq.myaquariumsimulator.game.FishManager.fishes
import com.kroq.myaquariumsimulator.game.ItemManager.items
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import com.kroq.myaquariumsimulator.model.item.ItemDatabase
import com.kroq.myaquariumsimulator.model.shop.ShopTab

object GameManager {
    var state by mutableStateOf(GameState())
        private set

    fun initialize(newState: GameState) {
        state = newState
        AquariumManager.initialize(state.aquariumType)

        val initialFishes = FishDatabase.getFishByIds(state.ownedFishIds)

        fishes.clear()
        fishes.addAll(initialFishes)

        val initialItems = ItemDatabase.getItemByIds(state.ownedItemIds)

        items.clear()
        items.addAll(initialItems)
    }

    fun update(reducer: (GameState) -> GameState) {
        val newState = reducer(state)
        state = newState
        SaveManager.save(newState)
    }

    fun updateSelectedTab(tab: ShopTab) {
        update{ it.copy(selectedShopTab = tab) }
    }

    fun resetGame() {
        update {
            it.copy(
                aquariumType = AquariumType.SMALL.name,
                ownedFishIds = setOf(),
                ownedItemIds = setOf(),
                coins = 2500,
                foodCount = 10
            )
        }
    }
}