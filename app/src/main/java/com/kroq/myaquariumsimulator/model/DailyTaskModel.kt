package com.kroq.myaquariumsimulator.model

data class DailyTask(
    val id: Int,
    val title: String,
    val progress: Int,
    val target: Int,
    val reward: Int,
    val isClaimed: Boolean
)