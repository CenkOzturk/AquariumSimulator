package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

object CoinLoop {

    private var job: Job? = null

    fun start(
        lifecycleOwner: LifecycleOwner,
        context: Context
    ) {

        job?.cancel()

        job = lifecycleOwner.lifecycleScope.launch {

            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {

                while (isActive) {
                    val fishCount = GameManager.state.ownedFishIds.size

                    GameManager.update(context) {
                        it.copy(coins = it.coins + fishCount)
                    }

                    delay(10000)
                }
            }
        }
    }

    fun stop() {
        job?.cancel()
        job = null
    }
}