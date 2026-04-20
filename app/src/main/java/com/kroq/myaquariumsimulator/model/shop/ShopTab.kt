package com.kroq.myaquariumsimulator.model.shop

import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.toShopItem

enum class ShopTab {
    FISH,
    AQUARIUM,
    ITEMS
}

fun ShopTab.items(): List<ShopItem> {
    return when (this) {
        ShopTab.AQUARIUM -> AquariumType.entries.map {
            it.toShopItem()
        }

        ShopTab.FISH -> listOf(
            ShopItem(10, "Fish 1", 3, "🐟"),
            ShopItem(11, "Fish 2", 5, "🐠"),
            ShopItem(12, "Fish 3", 8, "🐡")
        )

        ShopTab.ITEMS -> listOf(
            ShopItem(20, "Plant", 2, "🌿"),
            ShopItem(21, "Rock", 4, "🪨"),
            ShopItem(22, "Coral", 6, "🪸")
        )
    }
}