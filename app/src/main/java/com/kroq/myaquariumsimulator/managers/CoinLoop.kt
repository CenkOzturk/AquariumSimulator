package com.kroq.myaquariumsimulator.managers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kroq.myaquariumsimulator.data.Constants.INCOME_CYCLE_SECONDS
import com.kroq.myaquariumsimulator.model.fish.coinMultiplier
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

object CoinLoop {
    private var job: Job? = null

    fun start(
        lifecycleOwner: LifecycleOwner
    ) {
        job?.cancel()
        job = lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                while (isActive) {
                    var totalIncome = 0
                    FishManager.fishes.forEach { fish ->
                        totalIncome += fish.income * fish.coinMultiplier()
                    }

                    CoinManager.addCoins(totalIncome)

                    /*CoinManager.spawnCoin(
                        x = fish.move.x,
                        y = fish.move.y,
                        direction = fish.move.direction,
                        count = income,
                        amount = 1
                    )*/

                    delay(INCOME_CYCLE_SECONDS)
                }
            }
        }
    }

    fun stop() {
        job?.cancel()
        job = null
    }
}