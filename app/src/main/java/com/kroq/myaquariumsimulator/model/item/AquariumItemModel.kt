package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class AquariumItemModel(
    val id: Int,
    val type: ItemType,
    val resId: Int
)

fun AquariumItemModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        title = type.name,
        price = 100,
        icon = "🌿",
    )
}