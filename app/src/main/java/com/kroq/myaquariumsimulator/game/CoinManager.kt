package com.kroq.myaquariumsimulator.game

import android.content.Context


object CoinManager {

    fun addCoins(context: Context, amount: Int) {
        GameManager.update(context) {
            it.copy(coins = it.coins + amount)
        }
    }

    fun spendCoins(context: Context, amount: Int): Boolean {

        if (GameManager.state.coins < amount) return false

        GameManager.update(context) {
            it.copy(coins = it.coins - amount)
        }

        return true
    }
}