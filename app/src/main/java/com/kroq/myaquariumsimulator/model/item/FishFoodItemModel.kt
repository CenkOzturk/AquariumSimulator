package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab

data class FishFoodItemModel(
    val id: Int,
    val type: ItemType = ItemType.FISH_FOOD,
    val resId: Int = 0,
    val price: Int,
    val foodAmount: Int = 0
)

fun FishFoodItemModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        type = ShopTab.ITEMS,
        titleResId = R.string.fish_food,
        price = price,
        icon = "\uD83C\uDF64",
        extraInfo = foodAmount.toString(),
        isConsumable = true
    )
}