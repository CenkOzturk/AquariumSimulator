package com.kukurodev.mykukuroaquarium.model.extras

data class DirtParticleModel(
    val id: Long = System.nanoTime(),
    val x: Float,
    val y: Float,
    val speed: Float
)