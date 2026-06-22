package com.kroq.myaquariumsimulator.game

import android.content.Context
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase
import com.kroq.myaquariumsimulator.model.item.toShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.utils.Utils

object FishFoodManager {
    fun buyFood(itemId: Int) {
        CoinManager.purchaseItem(
            ShopTab.ITEMS,
            FishFoodItemDatabase.getAllFishFeed().map { it.toShopItem() },
            itemId
        )
    }

    //TODO balık seviyesine ve oyuncu seviyesine bağlı olarak değiştirilecek şekilde tekrar ayalanacak
    fun consumeFood() {
        if (GameManager.state.foodCount <= 0) {
            Utils.showToast(R.string.no_food_error)
            return
        } else {
            updateFood(-1)
        }
    }

    fun updateFood(value: Int) {
        GameManager.update { it.copy(foodCount = it.foodCount + value) }
    }
}