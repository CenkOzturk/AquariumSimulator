package com.kroq.myaquariumsimulator.model.shop

import com.kroq.myaquariumsimulator.game.GameManager
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.toShopItem
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import com.kroq.myaquariumsimulator.model.fish.toShopItem
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase
import com.kroq.myaquariumsimulator.model.item.ItemDatabase
import com.kroq.myaquariumsimulator.model.item.toShopItem

enum class ShopTab {
    FISH,
    AQUARIUM,
    ITEMS
}

fun ShopTab.items(): List<ShopItem> {
    return when (this) {
        ShopTab.AQUARIUM -> {
            AquariumType.entries
                .filter {
                    it.ordinal > AquariumType.valueOf(GameManager.state.aquariumType)
                        .ordinal
                }
                .map { it.toShopItem() }
        }

        ShopTab.FISH -> {
            FishDatabase.getAllFishes()
                .filter { it.id !in GameManager.state.ownedFishIds }
                .map { it.toShopItem() }
        }

        ShopTab.ITEMS ->
            buildList {
                addAll(FishFoodItemDatabase.getAllFishFeed().map { it.toShopItem() } )
                addAll(
                    ItemDatabase.getAllItems()
                        .filter { it.id !in GameManager.state.ownedItemIds }
                        .map { it.toShopItem() } )
            }
    }
}