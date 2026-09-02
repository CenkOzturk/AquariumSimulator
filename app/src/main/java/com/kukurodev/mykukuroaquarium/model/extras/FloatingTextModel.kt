package com.kukurodev.mykukuroaquarium.model.extras

data class FloatingTextModel(
    val id: Long,
    val amount: Int,
    val x: Float,
    val y: Float,
    val alpha: Float = 1f
)