package com.kroq.myaquariumsimulator.model.aquarium

data class AquariumModel(
    val type: AquariumType,
    val offsetX: Float,
    val offsetY: Float,
    val width: Float,
    val height: Float,
    val color: Long
)