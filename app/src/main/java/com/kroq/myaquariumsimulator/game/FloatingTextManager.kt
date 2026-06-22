package com.kroq.myaquariumsimulator.game

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.FloatingText

object FloatingTextManager {
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
}