package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class FishModel(
    val id: Int,
    val nameResId: Int,
    val resId: Int,
    val price: Int,
    val income: Int,
    val move: FishMoveModel,
    val fedUntil: Long = 50000L,
    val requirementType: RequirementType = RequirementType.FREE
)

fun FishModel.isFed(): Boolean {
    return fedUntil > System.currentTimeMillis()
}

fun FishModel.coinMultiplier(): Int {
    return if (isFed()) 2 else 1
}

fun FishModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        titleResId = nameResId,
        price = price,
        icon = "🐟",
        requiredTier = requirementType.toPlayerTier()
    )
}


