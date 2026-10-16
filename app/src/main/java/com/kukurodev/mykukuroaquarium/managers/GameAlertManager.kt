package com.kukurodev.mykukuroaquarium.managers

import com.kukurodev.mykukuroaquarium.model.SoundEffect
import com.kukurodev.mykukuroaquarium.model.alert.GameAlertModel
import com.kukurodev.mykukuroaquarium.model.alert.GameAlertType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object GameAlertManager {
    private val _currentAlert = MutableStateFlow<GameAlertModel?>(null)
    val currentAlert: StateFlow<GameAlertModel?> = _currentAlert.asStateFlow()

    private var dismissJob: Job? = null

    fun show(
        message: String,
        type: GameAlertType = GameAlertType.WARNING,
        icon: String? = null
    ) {
        dismissJob?.cancel()

        val resolvedType = when {
            message.contains("coin", ignoreCase = true) || message.contains("para", ignoreCase = true) -> GameAlertType.CURRENCY
            message.contains("food", ignoreCase = true) || message.contains("yem", ignoreCase = true) -> GameAlertType.FOOD
            message.contains("clean", ignoreCase = true) || message.contains("temiz", ignoreCase = true) -> GameAlertType.CLEANER
            message.contains("claimed", ignoreCase = true) || message.contains("alındı", ignoreCase = true) -> GameAlertType.SUCCESS
            else -> type
        }

        val resolvedIcon = icon ?: when {
            message.contains("coin", ignoreCase = true) || message.contains("para", ignoreCase = true) -> "💰"
            message.contains("food", ignoreCase = true) || message.contains("yem", ignoreCase = true) -> "🍤"
            message.contains("clean", ignoreCase = true) || message.contains("temiz", ignoreCase = true) -> "🧽"
            resolvedType == GameAlertType.SUCCESS -> "✨"
            else -> resolvedType.defaultIcon
        }

        val alert = GameAlertModel(
            id = System.currentTimeMillis(),
            message = message,
            type = resolvedType,
            icon = resolvedIcon
        )

        CoroutineScope(Dispatchers.Main).launch {
            AudioManager.playEffect(SoundEffect.BUBBLE_POP)

            if (_currentAlert.value != null) {
                _currentAlert.value = null
                delay(120)
            }

            _currentAlert.value = alert

            dismissJob = launch {
                delay(2800)
                if (_currentAlert.value?.id == alert.id) {
                    _currentAlert.value = null
                }
            }
        }
    }

    fun dismiss() {
        dismissJob?.cancel()
        _currentAlert.value = null
    }
}
