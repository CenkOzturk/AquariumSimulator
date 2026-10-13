package com.kukurodev.mykukuroaquarium.model.achievement

data class AchievementModel(
    val id: Int,
    val titleResId: Int,
    val descriptionResId: Int,
    val icon: String,
    val rewardCoins: Int
)
