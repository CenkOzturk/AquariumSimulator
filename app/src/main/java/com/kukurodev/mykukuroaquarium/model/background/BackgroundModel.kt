package com.kukurodev.mykukuroaquarium.model.background

import androidx.compose.ui.graphics.Color

data class BackgroundModel(
    val id: Int,
    val nameResId: Int,
    val resId: Int,
    val dominantColor: Color,
    val waterColorTop: Color,
    val waterColorBottom: Color,
    val tankFrameColor: Color
)
