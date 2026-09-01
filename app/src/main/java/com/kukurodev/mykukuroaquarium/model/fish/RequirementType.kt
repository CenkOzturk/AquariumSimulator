package com.kukurodev.mykukuroaquarium.model.fish

import com.kukurodev.mykukuroaquarium.model.GameProgress
import com.kukurodev.mykukuroaquarium.model.PlayerTier
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumType
import com.kukurodev.mykukuroaquarium.model.item.ItemType

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
                    progress.ownedItems.contains(ItemType.ROCK_SMALL)
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