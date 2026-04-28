package com.kroq.myaquariumsimulator.model.shop

import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.game.GameManager
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.toShopItem
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import com.kroq.myaquariumsimulator.model.fish.toShopItem
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

        ShopTab.ITEMS -> listOf(
            ShopItem(20, "Plant", 2, "🌿"),
            ShopItem(21, "Rock", 4, "🪨"),
            ShopItem(22, "Coral", 6, "🪸")
        )
    }
}