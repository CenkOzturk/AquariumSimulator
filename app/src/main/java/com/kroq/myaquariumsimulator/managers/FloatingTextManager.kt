package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.model.extras.FloatingTextModel

object FloatingTextManager {
    val texts = mutableStateListOf<FloatingTextModel>()

    fun spawn(text: String, x: Float, y: Float) {
        texts.add(
            FloatingTextModel(
                id = System.currentTimeMillis(),
                text = text,
                x = x,
                y = y
            )
        )
    }
}