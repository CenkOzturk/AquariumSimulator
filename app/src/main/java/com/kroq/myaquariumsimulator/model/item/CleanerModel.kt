package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class CleanerModel(
    val id: Int,
    val type: ItemType = ItemType.CLEANER,
    val nameResId: Int,
    val icon: Int,
    val price: Int,
    val cleanerCount: Int
)

fun CleanerModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        titleResId = nameResId,
        price = price,
        icon = "\uD83E\uDDFD",
        isConsumable = true
    )
}