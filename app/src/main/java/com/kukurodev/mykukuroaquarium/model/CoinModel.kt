package com.kukurodev.mykukuroaquarium.model

data class CoinModel(
    val id: Long,
    val x: Float,
    val startY: Float,
    val targetY: Float,
    val amount: Int = 1
)