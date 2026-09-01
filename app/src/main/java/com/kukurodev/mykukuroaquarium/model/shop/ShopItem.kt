package com.kukurodev.mykukuroaquarium.model.shop

import com.kukurodev.mykukuroaquarium.model.PlayerTier

data class ShopItem(
    val id: Int,
    val type: ShopTab,
    val titleResId: Int,
    val price: Int,
    val icon: String,
    val extraInfo: String,
    val requiredTier: PlayerTier = PlayerTier.FREE,
    val isConsumable: Boolean = false
)



