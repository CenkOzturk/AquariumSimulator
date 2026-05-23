package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.item.AquariumItemModel
import com.kroq.myaquariumsimulator.model.item.ItemType
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.game.GameManager.state
import com.kroq.myaquariumsimulator.utils.Utils

object ItemManager {
    val items = mutableStateListOf<AquariumItemModel>()
    fun buy(context: Context, itemId: Int) {
        val success = CoinManager.spendCoins(context,
            getAllItems().find { it.id == itemId }?.price ?: 0)

        if (!success) {
            Utils.showToast("Satın alma başarısız! Paran yetmedi :(")
            return
        }

        GameManager.update(context) {
            it.copy(
                ownedItemIds = it.ownedItemIds + itemId
            )
        }

        val currentIds = items.map { it.id }.toSet()
        val targetIds = state.ownedItemIds

        val newItemIds = targetIds - currentIds
        val newItems = getAllItems().filter { it.id in newItemIds }
        items.addAll(newItems)
    }

    fun getAllItems(): List<AquariumItemModel> {
        return listOf(
            AquariumItemModel(
                id = 300,
                type = ItemType.SAND,
                resId = R.drawable.sand,
                price = 20
            ),
            AquariumItemModel(
                id = 301,
                type = ItemType.ROCK_SMALL,
                resId = R.drawable.rock_small,
                price = 20
            ),
            AquariumItemModel(
                id = 302,
                type = ItemType.SEAWEED_SHORT,
                resId = R.drawable.seaweed_short,
                price = 50
            ),
            AquariumItemModel(
                id = 303,
                type = ItemType.STARFISH,
                resId = R.drawable.starfish,
                price = 50
            ),
        )
    }
}