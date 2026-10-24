package com.kukurodev.mykukuroaquarium.model.alert

import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.component.GameGradient

enum class GameAlertType(
    val defaultIcon: String,
    val gradient: GameGradient
) {
    WARNING(defaultIcon = "⚠️", gradient = GameColors.Red),
    SUCCESS(defaultIcon = "✨", gradient = GameColors.Leaf),
    INFO(defaultIcon = "🌊", gradient = GameColors.Ocean),
    CURRENCY(defaultIcon = "💰", gradient = GameColors.Sun),
    FOOD(defaultIcon = "🍤", gradient = GameColors.Sun),
    CLEANER(defaultIcon = "🧽", gradient = GameColors.Ocean)
}
