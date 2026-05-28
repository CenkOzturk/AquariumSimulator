package com.kroq.myaquariumsimulator.model

import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.fish.RequirementType
import com.kroq.myaquariumsimulator.model.item.ItemType

data class GameProgress(
    val aquariumType: AquariumType,
    val ownedItems: List<ItemType>
)

fun GameProgress.calculateTier(): PlayerTier {

    return when {
        RequirementType.GOLD.isSatisfied(this) -> PlayerTier.GOLD
        RequirementType.SILVER.isSatisfied(this) -> PlayerTier.SILVER
        RequirementType.BRONZE.isSatisfied(this) -> PlayerTier.BRONZE
        else -> PlayerTier.FREE
    }
}