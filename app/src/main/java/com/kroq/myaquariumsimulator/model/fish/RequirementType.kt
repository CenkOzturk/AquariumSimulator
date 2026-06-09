package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.model.GameProgress
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.item.ItemType

sealed class RequirementType {

    abstract fun isSatisfied(progress: GameProgress): Boolean

    object FREE : RequirementType() {
        override fun isSatisfied(progress: GameProgress) = true
    }

    object BRONZE : RequirementType() {
        override fun isSatisfied(progress: GameProgress): Boolean {
            return (progress.aquariumType == AquariumType.SMALL
                    || progress.aquariumType == AquariumType.MEDIUM) &&
                    progress.ownedItems.contains(ItemType.SAND)
        }
    }

    object SILVER : RequirementType() {
        override fun isSatisfied(progress: GameProgress): Boolean {
            return (progress.aquariumType == AquariumType.MEDIUM
                    || progress.aquariumType == AquariumType.LARGE) &&
                    progress.ownedItems.contains(ItemType.SEAWEED_SHORT)
        }
    }

    object GOLD : RequirementType() {
        override fun isSatisfied(progress: GameProgress): Boolean {
            return progress.aquariumType == AquariumType.LARGE &&
                    progress.ownedItems.containsAll(ItemType.entries)
        }
    }
}

fun RequirementType.toPlayerTier(): PlayerTier {
    return when (this) {
        RequirementType.FREE -> PlayerTier.FREE
        RequirementType.BRONZE -> PlayerTier.BRONZE
        RequirementType.SILVER -> PlayerTier.SILVER
        RequirementType.GOLD -> PlayerTier.GOLD
    }
}