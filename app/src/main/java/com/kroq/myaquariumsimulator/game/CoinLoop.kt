package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kroq.myaquariumsimulator.data.Constants.INCOME_CYCLE_SECONDS
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

object CoinLoop {


    private var job: Job? = null

    fun start(
        context: Context,
        lifecycleOwner: LifecycleOwner
    ) {
        job?.cancel()
        job = lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                while (isActive) {
                    var totalIncome = 0
                    GameManager.state.ownedFishIds.forEach { fishID ->
                        totalIncome += FishDatabase.getAllFishes().find { it.id == fishID }!!.income
                    }

                    GameManager.update(context) {
                        it.copy(coins = it.coins + totalIncome)
                    }

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