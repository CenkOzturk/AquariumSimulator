package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.game.ItemManager

object ItemDatabase {
    private val items = listOf(
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
        )
    )

    fun getAllItems(): List<AquariumItemModel> {
        return items
    }

    fun getItemByIds(ids: Set<Int>): List<AquariumItemModel> {
        return items.filter { it.id in ids }
    }
}