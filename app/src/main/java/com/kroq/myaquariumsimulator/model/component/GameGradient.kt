package com.kroq.myaquariumsimulator.model.component

import androidx.compose.ui.graphics.Color

data class GameGradient(
    val top: Color,
    val light: Color,
    val base: Color,
    val dark: Color,
    val border: Color
)