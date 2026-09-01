package com.kukurodev.mykukuroaquarium.model.item

import com.kukurodev.mykukuroaquarium.model.fish.RequirementType
import com.kukurodev.mykukuroaquarium.model.fish.toPlayerTier
import com.kukurodev.mykukuroaquarium.model.shop.ShopItem
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab
import com.kukurodev.mykukuroaquarium.utils.Utils

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