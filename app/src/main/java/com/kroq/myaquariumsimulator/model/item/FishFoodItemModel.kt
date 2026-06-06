package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.model.fish.RequirementType
import com.kroq.myaquariumsimulator.model.fish.toPlayerTier
import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class FishFoodItemModel(
    val id: Int,
    val type: ItemType = ItemType.FISH_FOOD,
    val resId: Int = 0,
    val price: Int,
    val foodAmount: Int = 0,
    val requirementType: RequirementType = RequirementType.FREE,
    val isConsumable: Boolean = true
)

fun FishFoodItemModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        title = type.name + "x" + foodAmount,
        price = price,
        icon = "\uD83C\uDF64",
        requiredTier = requirementType.toPlayerTier(),
        isConsumable = isConsumable
    )
}