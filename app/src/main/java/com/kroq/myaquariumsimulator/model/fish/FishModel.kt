package com.kroq.myaquariumsimulator.model.fish

data class FishModel(
    val id: Int,
    val resId: Int,
    val x: Float,
    val y: Float,
    val targetX: Float,
    val targetY: Float,
    val speed: Float,
    val direction: Int,
    val phase: Float = (0..1000).random().toFloat(),
    val visible: Boolean = true
)