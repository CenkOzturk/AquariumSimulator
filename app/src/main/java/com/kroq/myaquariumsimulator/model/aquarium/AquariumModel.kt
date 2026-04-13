package com.kroq.myaquariumsimulator.model.aquarium

data class AquariumModel(
    val id: Int,
    val offsetX: Float,
    val offsetY: Float,
    val width: Float,
    val height: Float,
    val color: Long
)