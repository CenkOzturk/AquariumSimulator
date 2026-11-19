package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kukurodev.mykukuroaquarium.managers.FishManager.fishes
import com.kukurodev.mykukuroaquarium.managers.ItemManager.items
import com.kukurodev.mykukuroaquarium.model.GameState
import com.kukurodev.mykukuroaquarium.model.fish.FishDatabase
import com.kukurodev.mykukuroaquarium.model.item.ItemDatabase
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab

object GameManager {
    var state by mutableStateOf(GameState())
        private set

    fun initialize(newState: GameState) {
        state = newState
    }

    fun initializeGameScreen() {
        AquariumManager.initialize(state.aquariumType)

        val initialFishes = FishDatabase.getFishByIds(state.activeFishes)

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
        // Fresh state; sound settings and the tutorial step survive a reset.
        update {
            GameState(
                musicEnable = it.musicEnable,
                soundEffectEnable = it.soundEffectEnable,
                tutorialStep = it.tutorialStep
            )
        }
        CloudSyncManager.deleteCloudSave()
    }
}
