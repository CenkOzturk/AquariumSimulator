package com.kroq.myaquariumsimulator.game

import com.kroq.myaquariumsimulator.model.shop.ShopTab

object FishFoodManager {
    fun buyFood(itemId: Int, itemPrice: Int) {
        CoinManager.purchaseItem(
            ShopTab.ITEMS,
            itemPrice,
            itemId
        )
    }

    fun updateFood(value: Int) {
        GameManager.update { it.copy(foodCount = it.foodCount + value) }
    }
}