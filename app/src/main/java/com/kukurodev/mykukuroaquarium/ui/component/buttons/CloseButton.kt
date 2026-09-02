package com.kukurodev.mykukuroaquarium.ui.component.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.model.component.GameGradient

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    gradient: GameGradient,
    onClose: () -> Unit
) {
    GameCircleButton(
        modifier = modifier,
        gradient = gradient,
        icon = Icons.Rounded.Close,
        onClick = onClose,
        size = 40.dp
    )
}