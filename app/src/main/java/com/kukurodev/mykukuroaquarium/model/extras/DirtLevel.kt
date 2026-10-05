package com.kukurodev.mykukuroaquarium.model.extras

enum class DirtLevel(
    val minValue: Int,
    val incomeMultiplier: Float,
    val overlaySpotCount: Int,
    val overlayAlpha: Float,
    val blocksInteraction: Boolean
) {
    CLEAN(
        minValue = 0,
        incomeMultiplier = 1f,
        overlaySpotCount = 0,
        overlayAlpha = 0f,
        blocksInteraction = false
    ),

    LIGHT(
        minValue = 5,
        incomeMultiplier = 0.75f,
        overlaySpotCount = 20,
        overlayAlpha = 0.16f,
        blocksInteraction = false
    ),

    DIRTY(
        minValue = 8,
        incomeMultiplier = 0.5f,
        overlaySpotCount = 40,
        overlayAlpha = 0.24f,
        blocksInteraction = false
    ),

    VERY_DIRTY(
        minValue = 12,
        incomeMultiplier = 0.25f,
        overlaySpotCount = 80,
        overlayAlpha = 0.32f,
        blocksInteraction = false
    ),

    BLOCKED(
        minValue = 20,
        incomeMultiplier = 0f,
        overlaySpotCount = 150,
        overlayAlpha = 0.42f,
        blocksInteraction = true
    );

    companion object {
        fun fromValue(value: Int): DirtLevel {
            return entries.last { value >= it.minValue }
        }
    }
}