package com.kukurodev.mykukuroaquarium.model.welcome

import androidx.annotation.StringRes

data class GiftModel(
    val day: Int = 0,
    val coins: Int = 0,
    val food: Int = 0,
    @param:StringRes val rewardResId: Int = 0
)
