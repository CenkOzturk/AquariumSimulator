package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.mutableStateListOf
import com.kukurodev.mykukuroaquarium.model.item.AquariumItemModel
import com.kukurodev.mykukuroaquarium.managers.GameManager.state
import com.kukurodev.mykukuroaquarium.model.item.ItemDatabase
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab

object ItemManager {
    val items = mutableStateListOf<AquariumItemModel>()
    fun buy(itemId: Int, price: Int) {
        CoinManager.purchaseItem(
            ShopTab.ITEMS,
            price,
            itemId
        )
        syncWithGameState()
    }

    private fun syncWithGameState() {
        val currentIds = items.map { it.id }.toSet()
        val targetIds = state.ownedItemIds

        val newItemIds = targetIds - currentIds
        val newItems = ItemDatabase.getAllItems().filter { it.id in newItemIds }
        items.addAll(newItems)
    }

    fun updateItems(itemId: Int) {
        GameManager.update{ it.copy(ownedItemIds = it.ownedItemIds + itemId) }
    }
}