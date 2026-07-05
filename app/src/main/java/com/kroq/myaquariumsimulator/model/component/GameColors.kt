package com.kroq.myaquariumsimulator.model.component

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object GameColors {

    val WelcomeGift = GameGradient(
        top = Color(0xFFF7EEFF),
        light = Color(0xFFE2CCFF),
        base = Color(0xFFC39CFF),
        dark = Color(0xFF9B6AF3),
        border = Color(0xFF7C3AED)
    )

    val DailyTask = GameGradient(
        top = Color(0xFFF1FFF8),
        light = Color(0xFFD8FBE8),
        base = Color(0xFFB8F5D3),
        dark = Color(0xFF76DEA7),
        border = Color(0xFF33C47C)
    )

    val Shop = GameGradient(
        top = Color(0xFFFFF9EF),
        light = Color(0xFFFFF0D4),
        base = Color(0xFFFFD88C),
        dark = Color(0xFFF4A938),
        border = Color(0xFFE08700)
    )

    val Ocean = GameGradient(
        top = Color(0xFFF1FCFF),
        light = Color(0xFFD8F5FF),
        base = Color(0xFFB4ECFF),
        dark = Color(0xFF67D3F3),
        border = Color(0xFF2AA7D7)
    )

    val Gold = GameGradient(
        top = Color(0xFFFFFBE7),
        light = Color(0xFFFFF1A8),
        base = Color(0xFFFFE066),
        dark = Color(0xFFFFC93C),
        border = Color(0xFFF4A300)
    )

    val OceanGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF6FD3FF),
            Color(0xFF3BA7F5)
        )
    )

    val GrayGradient = Brush.verticalGradient(
        listOf(
            Color(0xFFD8E4EA),
            Color(0xFFAEBCC5)
        )
    )

    val GoldGradient = Brush.verticalGradient(
        listOf(
            Color(0xFFFFE082),
            Color(0xFFFFB300)
        )
    )
}