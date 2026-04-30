package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.mutableStateListOf


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

    //TEXT Layer
    val texts = mutableStateListOf<FloatingText>()

    fun spawn(text: String, x: Float, y: Float) {
        texts.add(
            FloatingText(
                id = System.currentTimeMillis(),
                text = text,
                x = x,
                y = y
            )
        )
    }

    fun update() {
        val newList = texts.mapNotNull { t ->

            val newY = t.y - 1.5f
            val newAlpha = t.alpha - 0.02f

            if (newAlpha <= 0f) null
            else t.copy(y = newY, alpha = newAlpha)
        }

        texts.clear()
        texts.addAll(newList)
    }

    data class FloatingText(
        val id: Long,
        val text: String,
        val x: Float,
        val y: Float,
        val alpha: Float = 1f
    )
}