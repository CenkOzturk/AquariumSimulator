package com.kukurodev.mykukuroaquarium.model.welcome

data class WelcomeGiftModel(
    val claimedDays: Int = 0,
    val claimedToday: Boolean = false,
    val lastClaimTime: Long = 0L
)