package com.kroq.myaquariumsimulator.model

data class FloatingText(
    val id: Long,
    val text: String,
    val x: Float,
    val y: Float,
    val alpha: Float = 1f
)