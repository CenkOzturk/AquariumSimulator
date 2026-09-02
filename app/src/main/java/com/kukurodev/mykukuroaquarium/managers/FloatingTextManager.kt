package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.mutableStateListOf
import com.kukurodev.mykukuroaquarium.model.extras.FloatingTextModel

object FloatingTextManager {
    val texts = mutableStateListOf<FloatingTextModel>()

    fun spawn(amount: Int, x: Float, y: Float) {
        texts.add(
            FloatingTextModel(
                id = System.currentTimeMillis(),
                amount = amount,
                x = x,
                y = y
            )
        )
    }
}