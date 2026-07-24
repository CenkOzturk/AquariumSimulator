package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class CleanerModel(
    val id: Int,
    val type: ItemType = ItemType.CLEANER,
    val name: String,
    val icon: Int,
    val price: Int,
    val cleanerCount: Int
)

fun CleanerModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        title = type.name + "x" + cleanerCount,
        price = price,
        icon = "\uD83C\uDF64",
        isConsumable = true
    )
}