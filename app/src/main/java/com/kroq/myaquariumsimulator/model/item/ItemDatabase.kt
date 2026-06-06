package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.fish.RequirementType

object ItemDatabase {
    private val items = listOf(
        AquariumItemModel(
            id = 300,
            type = ItemType.SAND,
            resId = R.drawable.sand,
            price = 250
        ),
        AquariumItemModel(
            id = 301,
            type = ItemType.ROCK_SMALL,
            resId = R.drawable.rock_small,
            price = 1000,
            requirementType = RequirementType.SILVER
        ),
        AquariumItemModel(
            id = 302,
            type = ItemType.SEAWEED_SHORT,
            resId = R.drawable.seaweed_short,
            price = 500,
            requirementType = RequirementType.BRONZE
        ),
        AquariumItemModel(
            id = 303,
            type = ItemType.STARFISH,
            resId = R.drawable.starfish,
            price = 50,
            requirementType = RequirementType.GOLD
        )
    )

    fun getAllItems(): List<AquariumItemModel> {
        return items
    }

    fun getItemByIds(ids: Set<Int>): List<AquariumItemModel> {
        return items.filter { it.id in ids }
    }
}