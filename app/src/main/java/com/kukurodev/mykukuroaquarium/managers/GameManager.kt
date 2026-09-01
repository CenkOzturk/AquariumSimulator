package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kukurodev.mykukuroaquarium.data.GameDefaults
import com.kukurodev.mykukuroaquarium.managers.FishManager.fishes
import com.kukurodev.mykukuroaquarium.managers.ItemManager.items
import com.kukurodev.mykukuroaquarium.model.GameState
import com.kukurodev.mykukuroaquarium.model.fish.FishDatabase
import com.kukurodev.mykukuroaquarium.model.item.ItemDatabase
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeState

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

        UpgradeManager.initializeUpgrades()
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
                aquariumType = GameDefaults.STARTING_AQUARIUM,
                ownedFishIds = emptySet(),
                ownedItemIds = emptySet(),
                ownedUpgrades = UpgradeState(emptyList()),
                coins = GameDefaults.STARTING_COINS,
                foodCount = GameDefaults.STARTING_FOOD,
                cleanerCount = GameDefaults.CLEANER_COUNT,
                dailyTask = null,
                welcomeGiftDay = 0,
                welcomeGiftClaimed = false,
                tutorialCompleted = false,
                goldFishUnlocked = false,
                lastGoldFishTime = 0L
            )
        }
    }
}