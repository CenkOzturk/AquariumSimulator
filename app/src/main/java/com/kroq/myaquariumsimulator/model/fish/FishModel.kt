package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class FishModel(
    val id: Int,
    val resId: Int,
    val x: Float,
    val y: Float,
    val targetX: Float,
    val targetY: Float,
    val speed: Float,
    val direction: Int,
    val price: Int,
    val income: Int,
    val requirementType: RequirementType = RequirementType.FREE,
    val phase: Float = (0..1000).random().toFloat()
)

fun FishModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        title = "Fish $id",
        price = price,
        icon = "🐟",
        requiredTier = requirementType.toPlayerTier()
    )
}

fun RequirementType.toPlayerTier(): PlayerTier {
    return when (this) {
        RequirementType.FREE -> PlayerTier.FREE
        RequirementType.BRONZE -> PlayerTier.BRONZE
        RequirementType.SILVER -> PlayerTier.SILVER
        RequirementType.GOLD -> PlayerTier.GOLD
    }
}


