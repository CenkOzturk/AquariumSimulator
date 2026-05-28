package com.kroq.myaquariumsimulator.model

enum class PlayerTier {
    FREE,
    BRONZE,
    SILVER,
    GOLD
}

fun PlayerTier.canAccess(required: PlayerTier): Boolean {
    return this.ordinal >= required.ordinal
}