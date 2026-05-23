package com.kroq.myaquariumsimulator.game

import android.content.Context
import com.kroq.myaquariumsimulator.model.item.AquariumItemModel
import com.kroq.myaquariumsimulator.model.item.ItemType
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.shop.ShopItem

object ItemManager {
    fun buy(context: Context, item: ShopItem) {
        GameManager.update(context) { state ->
            state.copy(
                ownedItemIds =
                    state.ownedItemIds + item.id
            )
        }
    }

    fun getAllItems(): List<AquariumItemModel> {
        return listOf(
            AquariumItemModel(
                id = 0,
                type = ItemType.SAND,
                resId = R.drawable.sand
            ),
            AquariumItemModel(
                id = 1,
                type = ItemType.ROCK_SMALL,
                resId = R.drawable.rock_small
            ),
            AquariumItemModel(
                id = 2,
                type = ItemType.SEAWEED_SHORT,
                resId = R.drawable.seaweed_short
            ),
            AquariumItemModel(
                id = 3,
                type = ItemType.STARFISH,
                resId = R.drawable.starfish
            ),
        )
    }
}