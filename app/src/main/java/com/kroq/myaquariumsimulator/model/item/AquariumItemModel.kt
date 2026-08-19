package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.model.fish.RequirementType
import com.kroq.myaquariumsimulator.model.fish.toPlayerTier
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.utils.Utils

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
        type = ShopTab.AQUARIUM,
        titleResId = nameResId,
        price = price,
        icon = "🌿",
        extraInfo = Utils.emptyString(),
        requiredTier = requirementType.toPlayerTier(),
        isConsumable = isConsumable
    )
}