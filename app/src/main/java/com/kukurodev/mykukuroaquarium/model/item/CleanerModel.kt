package com.kukurodev.mykukuroaquarium.model.item

import com.kukurodev.mykukuroaquarium.model.shop.ShopItem
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab

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
        type = ShopTab.AQUARIUM,
        titleResId = nameResId,
        price = price,
        icon = "🧽",
        imgResId = icon,
        extraInfo = cleanerCount.toString(),
        isConsumable = true
    )
}
