package com.kroq.myaquariumsimulator.model.shop

import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.game.GameManager
import com.kroq.myaquariumsimulator.game.ItemManager
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.toShopItem
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import com.kroq.myaquariumsimulator.model.fish.toShopItem
import com.kroq.myaquariumsimulator.model.item.toShopItem
import com.kroq.myaquariumsimulator.utils.Utils
import kotlin.text.contains

enum class ShopTab {
    FISH,
    AQUARIUM,
    ITEMS
}

fun ShopTab.items(): List<ShopItem> {
    return when (this) {
        ShopTab.AQUARIUM -> {
            AquariumType.entries
                .filter { it.name !in GameManager.state.aquariumType }
                .map { it.toShopItem() }
        }

        ShopTab.FISH -> {
            FishDatabase.getAllFishes()
                .filter { it.id !in GameManager.state.ownedFishIds }
                .map { it.toShopItem() }
        }

        ShopTab.ITEMS ->
            ItemManager.getAllItems()
                .filter { it.id !in GameManager.state.ownedItemIds }
                .map { it.toShopItem()
        }
    }
}