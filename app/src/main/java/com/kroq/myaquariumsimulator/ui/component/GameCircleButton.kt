package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.model.component.GameGradient
import com.kroq.myaquariumsimulator.utils.Utils.emptyString


@Composable
fun GameCircleButton(
    modifier: Modifier = Modifier,
    gradient: GameGradient = GameColors.WelcomeGift,
    icon: ImageVector,
    size: Dp = 52.dp,
    onClick: () -> Unit
) {

    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.92f else 1f,
        animationSpec = tween(80),
        label = emptyString()
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .size(size)
            .shadow(
                elevation = 10.dp,
                shape = CircleShape,
                ambientColor = gradient.dark.copy(alpha = .45f),
                spotColor = gradient.dark.copy(alpha = .45f)
            )
            .clip(CircleShape)
            .background(
                Brush.verticalGradient(
                    listOf(
                        gradient.top,
                        gradient.light,
                        gradient.base,
                        gradient.dark
                    )
                )
            )
            .border(
                2.dp,
                gradient.border,
                CircleShape
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                        onClick()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = gradient.border,
            modifier = Modifier.size(22.dp)
        )
    }
}