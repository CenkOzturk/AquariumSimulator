package com.kukurodev.mykukuroaquarium.model.aquarium

data class AquariumModel(
    val type: AquariumType,
    val nameResId: Int,
    val offsetX: Float,
    val offsetY: Float,
    val width: Float,
    val height: Float,
    val color: Long,
    val fishCount: Int,
    val price: Int
)