package com.kroq.myaquariumsimulator.model.shop

import com.kroq.myaquariumsimulator.model.PlayerTier

data class ShopItem(
    val id: Int,
    val title: String,
    val price: Int,
    val icon: String,
    val requiredTier: PlayerTier = PlayerTier.FREE
)



