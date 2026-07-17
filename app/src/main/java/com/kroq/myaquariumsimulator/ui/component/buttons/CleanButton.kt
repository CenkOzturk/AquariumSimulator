package com.kroq.myaquariumsimulator.ui.component.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import com.kroq.myaquariumsimulator.model.component.GameColors

@Composable
fun CleanButton(
    visible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {

        GameCircleButton (
            icon = Icons.Rounded.Clear,
            gradient = GameColors.Ocean,
            onClick = onClick
        )
    }
}