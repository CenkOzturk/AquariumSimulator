package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class FishModel(
    val id: Int,
    val resId: Int,
    val price: Int,
    val income: Int,
    val move: FishMoveModel,
    val requirementType: RequirementType = RequirementType.FREE
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


