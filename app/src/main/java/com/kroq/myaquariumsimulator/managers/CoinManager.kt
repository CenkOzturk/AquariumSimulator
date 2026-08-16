package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.CoinModel
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.getFoodCountByIds
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.isFood
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.model.task.DailyTaskType
import com.kroq.myaquariumsimulator.utils.Utils


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

    fun spawnCoin(x: Float, y: Float, direction: Int = 1, count: Int, amount: Int = 1) {
        repeat(count) { index ->
            val spawnX = getSpawnX(fishX = x, direction = direction, index = index, count = count)

            _coins += CoinModel(
                id = nextCoinId++,
                x = spawnX,
                startY = y,
                targetY = AquariumManager.currentAquariumBottom(),
                amount = amount
            )
        }
    }

    fun removeCoin(id: Long) {
        _coins.removeAll {
            it.id == id
        }
    }

    private fun getSpawnX(fishX: Float, direction: Int, index: Int, count: Int): Float {
        if (count <= 1) return fishX

        val maxOffset = 48f
        val offset = maxOffset * index / (count - 1)

        return if (direction > 0) { fishX + offset } else { fishX - offset }
    }

    fun getCoinSize(amount: Int): Dp {
        return when {
            amount <= 1 -> 64.dp
            amount <= 5 -> 80.dp
            amount <= 20 -> 100.dp
            amount <= 100 -> 128.dp
            amount <= 200 -> 156.dp
            else -> 104.dp
        }
    }
}