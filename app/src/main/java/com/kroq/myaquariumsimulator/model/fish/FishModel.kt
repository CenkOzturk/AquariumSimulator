package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.model.shop.ShopItem

data class FishModel(
    val id: Int,
    val resId: Int,
    val x: Float,
    val y: Float,
    val targetX: Float,
    val targetY: Float,
    val speed: Float,
    val direction: Int,
    val phase: Float = (0..1000).random().toFloat(),
    val visible: Boolean = true
)

fun FishModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        title = "Fish $id",
        price = getFishPrice(id),
        icon = "🐟"
    )
}

fun getFishPrice(id: Int): Int {
    return when (id) {
        1 -> 5
        2 -> 10
        3 -> 15
        4 -> 25
        5 -> 40
        else -> 10
    }
}


