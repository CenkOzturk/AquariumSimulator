package com.kroq.myaquariumsimulator.managers

import com.kroq.myaquariumsimulator.data.Constants.FEED_DURATION
import com.kroq.myaquariumsimulator.managers.FishManager.fishes
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.model.task.DailyTaskType

object FishFoodManager {
    fun feedFish(fishId: Int) {
        val index = fishes.indexOfFirst { it.id == fishId }

        if (index == -1) return

        fishes[index] = fishes[index].copy(
            fedUntil = System.currentTimeMillis() + FEED_DURATION
        )
        updateFood(- fishes[index].income)
        DailyTaskManager.addProgress(DailyTaskType.FEED_FISH)
    }
    fun buyFood(itemId: Int, itemPrice: Int) {
        CoinManager.purchaseItem(
            ShopTab.ITEMS,
            itemPrice,
            itemId,
            onSuccess = {
                DailyTaskManager.addProgress(DailyTaskType.BUY_FISH_FEED,
                    FishFoodItemDatabase.getFoodCountByIds(itemId))
            }
        )
    }

    fun updateFood(value: Int) {
        GameManager.update { it.copy(foodCount = it.foodCount + value) }
    }
}