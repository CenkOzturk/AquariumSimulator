package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.model.fish.RequirementType
import com.kroq.myaquariumsimulator.model.fish.toPlayerTier
import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class AquariumItemModel(
    val id: Int,
    val nameResId: Int,
    val type: ItemType,
    val resId: Int,
    val price: Int,
    val requirementType: RequirementType = RequirementType.FREE,
    val isConsumable: Boolean = false
)

fun AquariumItemModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        titleResId = nameResId,
        price = price,
        icon = "🌿",
        requiredTier = requirementType.toPlayerTier(),
        isConsumable = isConsumable
    )
}