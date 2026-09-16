package com.kukurodev.mykukuroaquarium.managers

import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.model.item.CleanerDatabase
import com.kukurodev.mykukuroaquarium.utils.Utils

object CleanerManager {
    fun buyCleaner(id: Int, price: Int) {
        val cleaner = CleanerDatabase.getCleaner(id) ?: return
        CoinManager.hasEnoughCoin(price)
        GameManager.update {
            it.copy(
                cleanerCount = it.cleanerCount + cleaner.cleanerCount
            )
        }
    }

    fun cleanDirt() {
        if (GameManager.state.cleanerCount == 0) {
            Utils.showToast(R.string.no_cleaner_error)
            return
        }
        GameManager.update {
            it.copy(
                cleanerCount = it.cleanerCount - 1,
                dirtParticleCount = 0
            )
        }
        DirtManager.clear()
    }

    fun updateCleaner(value: Int) {
        GameManager.update {
            it.copy(
                cleanerCount = it.cleanerCount + value,
                dirtParticleCount = 0
            )
        }
    }
}