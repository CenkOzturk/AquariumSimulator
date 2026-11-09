package com.kukurodev.mykukuroaquarium.managers

import com.kukurodev.mykukuroaquarium.data.Constants.FEED_DURATION
import com.kukurodev.mykukuroaquarium.managers.FishManager.fishes
import com.kukurodev.mykukuroaquarium.model.item.FishFoodItemDatabase
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab
import com.kukurodev.mykukuroaquarium.model.task.DailyTaskType
import com.kukurodev.mykukuroaquarium.notifications.NotificationHelper
import com.kukurodev.mykukuroaquarium.utils.Utils

object FishFoodManager {
    fun feedFish(fishId: Int) {
        val index = fishes.indexOfFirst { it.id == fishId }

        if (index == -1) return

        val now = System.currentTimeMillis()
        fishes[index] = fishes[index].copy(
            fedUntil = now + FEED_DURATION
        )
        updateFood(- fishes[index].income)
        GameManager.update {
            it.copy(
                fishLastFedTimes = it.fishLastFedTimes + (fishId to now)
            )
        }
        Utils.appContext?.let { context ->
            NotificationHelper.scheduleNotifications(context, now)
        }
        DailyTaskManager.addProgress(DailyTaskType.FEED_FISH)
    }

    fun buyFood(itemId: Int, itemPrice: Int) {
        CoinManager.purchaseItem(
            ShopTab.ITEMS,
            itemPrice,
            itemId,
            onSuccess = {
                DailyTaskManager.addProgress(
                    DailyTaskType.BUY_FISH_FEED,
                    FishFoodItemDatabase.getFoodCountByIds(itemId)
                )
            }
        )
    }

    fun updateFood(value: Int) {
        GameManager.update { it.copy(foodCount = it.foodCount + value) }
    }
}
