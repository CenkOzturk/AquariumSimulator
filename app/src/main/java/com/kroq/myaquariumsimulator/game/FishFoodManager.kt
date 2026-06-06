package com.kroq.myaquariumsimulator.game

import android.content.Context
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase
import com.kroq.myaquariumsimulator.model.item.toShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab

object FishFoodManager {
    fun buyFood(context: Context, itemId: Int) {
        CoinManager.purchaseItem(
            context,
            ShopTab.ITEMS,
            FishFoodItemDatabase.getAllFishFeed().map { it.toShopItem() },
            itemId
        )
    }
}