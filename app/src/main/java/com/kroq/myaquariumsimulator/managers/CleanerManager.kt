package com.kroq.myaquariumsimulator.managers

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.item.CleanerDatabase
import com.kroq.myaquariumsimulator.utils.Utils

object CleanerManager {
    fun buyCleaner(id: Int, price: Int) {
        val cleaner = CleanerDatabase.get(id) ?: return

        if (!CoinManager.spendCoins(price)) {
            Utils.showToast(R.string.shop_no_coin_error)
            return
        }

        GameManager.update {
            it.copy(
                cleanerCount = it.cleanerCount + cleaner.cleanerCount
            )
        }
    }

    fun canClean(): Boolean {
        return GameManager.state.cleanerCount > 0
    }

    fun cleanDirt(): Boolean {
        if (!canClean()) {
            Utils.showToast(R.string.no_cleaner_error)
            return false
        }

        GameManager.update {
            it.copy(
                cleanerCount = it.cleanerCount - 1,
                dirtParticleCount = 0
            )
        }
        DirtManager.clear()
        return true
    }
}