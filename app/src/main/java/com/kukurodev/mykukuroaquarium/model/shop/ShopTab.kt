package com.kukurodev.mykukuroaquarium.model.shop

import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumType
import com.kukurodev.mykukuroaquarium.model.aquarium.toShopItem
import com.kukurodev.mykukuroaquarium.model.fish.FishDatabase
import com.kukurodev.mykukuroaquarium.model.fish.toShopItem
import com.kukurodev.mykukuroaquarium.model.item.CleanerDatabase
import com.kukurodev.mykukuroaquarium.model.item.FishFoodItemDatabase
import com.kukurodev.mykukuroaquarium.model.item.ItemDatabase
import com.kukurodev.mykukuroaquarium.model.item.toShopItem

enum class ShopTab {
    FISH,
    AQUARIUM,
    ITEMS
}

fun ShopTab.items(): List<ShopItem> {
    return when (this) {
        ShopTab.AQUARIUM ->
            buildList {
                addAll(
                    AquariumType.entries
                        .filter {
                            it.ordinal > AquariumType.valueOf(GameManager.state.aquariumType).ordinal
                        }
                        .map { it.toShopItem() }
                )
                addAll(FishFoodItemDatabase.getAllFishFeed().map { it.toShopItem() })
                addAll(CleanerDatabase.getAllCleaners().map { it.toShopItem() })
            }

        ShopTab.FISH -> {
            FishDatabase.getAllFishes()
                .filter { it.id !in GameManager.state.ownedFishIds }
                .map { it.toShopItem() }
        }

        ShopTab.ITEMS ->
            ItemDatabase.getAllItems()
                .filter { it.id !in GameManager.state.ownedItemIds }
                .map { it.toShopItem() }
    }
}
