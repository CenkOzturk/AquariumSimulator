package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.item.AquariumItemModel
import com.kroq.myaquariumsimulator.managers.GameManager.state
import com.kroq.myaquariumsimulator.model.item.ItemDatabase
import com.kroq.myaquariumsimulator.model.shop.ShopTab

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