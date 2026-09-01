package com.kukurodev.mykukuroaquarium.model

import com.kukurodev.mykukuroaquarium.managers.AquariumManager
import com.kukurodev.mykukuroaquarium.managers.ItemManager
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumType
import com.kukurodev.mykukuroaquarium.model.fish.RequirementType
import com.kukurodev.mykukuroaquarium.model.item.ItemType

data class GameProgress(
    val aquariumType: AquariumType = AquariumManager.currentAquarium.type,
    val ownedItems: List<ItemType> = ItemManager.items.map { it.type }
)

fun GameProgress.calculateTier(): PlayerTier {
    return when {
        RequirementType.GOLD.isSatisfied(this) -> PlayerTier.GOLD
        RequirementType.SILVER.isSatisfied(this) -> PlayerTier.SILVER
        RequirementType.BRONZE.isSatisfied(this) -> PlayerTier.BRONZE
        else -> PlayerTier.FREE
    }
}