package com.kroq.myaquariumsimulator.game

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.getFoodCountByIds
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.isFood
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.utils.Utils


object CoinManager {
    fun purchaseItem(
        shopTab: ShopTab,
        price: Int,
        shopItemId: Int,
        onFail: () -> Unit = {
            Utils.showToast(R.string.shop_no_coin_error)
        }
    ) {
        if (!spendCoins(price)) {
            onFail()
            return
        }

        when (shopTab) {
            ShopTab.ITEMS -> controlFoodAndUpdate(shopItemId)
            ShopTab.FISH -> FishManager.updateFish(shopItemId)
            ShopTab.AQUARIUM -> {}
        }
    }

    fun controlFoodAndUpdate(itemId: Int) {
        if (isFood(itemId)) {
            FishFoodManager.updateFood(getFoodCountByIds(itemId))
        } else {
            ItemManager.updateItems(itemId)
        }
    }

    fun spendCoins(amount: Int): Boolean {
        if (GameManager.state.coins < amount) return false

        GameManager.update { it.copy(coins = it.coins - amount) }

        return true
    }

    fun addCoins(amount: Int) {
        GameManager.update { it.copy(coins = it.coins + amount) }
    }
}