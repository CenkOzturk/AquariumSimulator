package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.data.Constants.COIN_SIZE
import com.kroq.myaquariumsimulator.data.Constants.FISH_SIZE
import com.kroq.myaquariumsimulator.model.CoinModel
import com.kroq.myaquariumsimulator.model.fish.FishModel
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.getFoodCountByIds
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.isFood
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.model.task.DailyTaskType
import com.kroq.myaquariumsimulator.utils.Utils
import kotlin.random.Random


object CoinManager {
    private val _coins = mutableStateListOf<CoinModel>()
    val coins: List<CoinModel>
        get() = _coins

    private var nextCoinId = 0L
    fun purchaseItem(
        shopTab: ShopTab,
        price: Int,
        shopItemId: Int,
        onFail: () -> Unit = {
            Utils.showToast(R.string.shop_no_coin_error)
        },
        onSuccess: () -> Unit = {}
    ) {
        if (spendCoins(price)) {
            onSuccess()
        } else {
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
        DailyTaskManager.addProgress(DailyTaskType.COLLECT_COIN, amount)
    }

    fun spawnCoin(
        fish: FishModel,
        targetY: Float,
        count: Int,
        amount: Int = 1
    ) {
        repeat(count) { index ->
            val spawnX = getSpawnX(
                fishX = fish.move.x,
                direction = fish.move.direction,
                index = index,
                count = count
            )

            _coins += CoinModel(
                id = nextCoinId++,
                x = spawnX,
                startY = fish.move.y,
                targetY = targetY,
                amount = amount
            )
        }
    }

    fun removeCoin(id: Long) {
        _coins.removeAll {
            it.id == id
        }
    }

    private fun getSpawnX(
        fishX: Float,
        direction: Int,
        index: Int,
        count: Int
    ): Float {
        if (count <= 1) {
            return fishX
        }
        val maxOffset = COIN_SIZE * 0.75f
        val offset = maxOffset * index / (count - 1)

        return if (direction > 0) {
            fishX + offset
        } else {
            fishX - offset
        }
    }
}