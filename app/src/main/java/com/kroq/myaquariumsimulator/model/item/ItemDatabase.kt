package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.fish.RequirementType

object ItemDatabase {
    private val items = listOf(
        AquariumItemModel(
            id = 300,
            nameResId = R.string.item_sand_name,
            type = ItemType.SAND,
            resId = R.drawable.sand,
            price = 750
        ),
        AquariumItemModel(
            id = 301,
            nameResId = R.string.item_seaweed_short_name,
            type = ItemType.SEAWEED_SHORT,
            resId = R.drawable.seaweed_short,
            price = 2500,
            requirementType = RequirementType.BRONZE
        ),
        AquariumItemModel(
            id = 302,
            nameResId = R.string.item_rock_small_name,
            type = ItemType.ROCK_SMALL,
            resId = R.drawable.rock_small,
            price = 7500,
            requirementType = RequirementType.SILVER
        ),
        AquariumItemModel(
            id = 303,
            nameResId = R.string.item_starfish_name,
            type = ItemType.STARFISH,
            resId = R.drawable.starfish,
            price = 25000,
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