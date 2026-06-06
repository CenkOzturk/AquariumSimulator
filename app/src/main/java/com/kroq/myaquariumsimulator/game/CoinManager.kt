package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.getFoodCountByIds
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.isFood
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.utils.Utils


object CoinManager {
    fun addCoins(context: Context, amount: Int) {
        GameManager.update(context) {
            it.copy(coins = it.coins + amount)
        }
    }

    fun purchaseItem(
        context: Context,
        shopTab: ShopTab,
        list: List<ShopItem>,
        shopItemId: Int,
        onFail: () -> Unit = { Utils.showToast("Satın alma başarısız! Paran yetmedi :(") }
    ) {
        val price = list.find { it.id == shopItemId }?.price ?: 0

        if (!spendCoins(context, price)) {
            onFail()
            return
        }

        GameManager.update(context) {
            when (shopTab) {
                ShopTab.ITEMS -> {
                    if (isFood(shopItemId)) {
                        it.copy(
                            foodCount = it.foodCount + getFoodCountByIds(shopItemId)
                        )
                    } else {
                        it.copy(
                            ownedItemIds = it.ownedItemIds + shopItemId
                        )
                    }
                }

                ShopTab.FISH -> {
                    it.copy(
                        ownedFishIds = it.ownedFishIds + shopItemId
                    )
                } else -> it
            }

        }
    }

    fun spendCoins(context: Context, amount: Int): Boolean {

        if (GameManager.state.coins < amount) return false

        GameManager.update(context) {
            it.copy(coins = it.coins - amount)
        }

        return true
    }

    //TEXT Layer
    val texts = mutableStateListOf<FloatingText>()

    fun spawn(text: String, x: Float, y: Float) {
        texts.add(
            FloatingText(
                id = System.currentTimeMillis(),
                text = text,
                x = x,
                y = y
            )
        )
    }

    fun update() {
        val newList = texts.mapNotNull { t ->

            val newY = t.y - 1.5f
            val newAlpha = t.alpha - 0.02f

            if (newAlpha <= 0f) null
            else t.copy(y = newY, alpha = newAlpha)
        }

        texts.clear()
        texts.addAll(newList)
    }

    data class FloatingText(
        val id: Long,
        val text: String,
        val x: Float,
        val y: Float,
        val alpha: Float = 1f
    )
}