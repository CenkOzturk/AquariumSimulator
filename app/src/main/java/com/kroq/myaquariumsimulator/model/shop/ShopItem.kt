package com.kroq.myaquariumsimulator.model.shop

import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.utils.Utils

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



