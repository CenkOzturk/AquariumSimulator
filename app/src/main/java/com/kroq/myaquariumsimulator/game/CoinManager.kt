package com.kroq.myaquariumsimulator.game

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.getFoodCountByIds
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.isFood
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.utils.Utils


object CoinManager {
    //TODO updatelenicek listeye gerek yok price gönder direkt
    fun purchaseItem(
        shopTab: ShopTab,
        list: List<ShopItem>,
        shopItemId: Int,
        onFail: () -> Unit = {
            Utils.showToast(R.string.shop_no_coin_error)
        }
    ) {
        val price = list.find { it.id == shopItemId }?.price ?: 0

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