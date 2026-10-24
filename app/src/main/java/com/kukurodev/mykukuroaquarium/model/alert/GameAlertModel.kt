package com.kukurodev.mykukuroaquarium.model.alert

data class GameAlertModel(
    val id: Long = System.currentTimeMillis(),
    val message: String,
    val type: GameAlertType = GameAlertType.WARNING,
    val icon: String = type.defaultIcon
)
