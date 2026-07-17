package com.kroq.myaquariumsimulator.model.extras

data class FloatingTextModel(
    val id: Long,
    val text: String,
    val x: Float,
    val y: Float,
    val alpha: Float = 1f
)