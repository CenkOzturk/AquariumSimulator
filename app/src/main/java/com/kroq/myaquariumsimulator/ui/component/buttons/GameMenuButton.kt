package com.kroq.myaquariumsimulator.ui.component.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.model.component.GameGradient

@Composable
fun GameMenuButton(
    text: String,
    modifier: Modifier = Modifier,
    gradient: GameGradient = GameColors.Ocean,
    isPrimary: Boolean = false,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) .97f else 1f,
        animationSpec = tween(80),
        label = ""
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .fillMaxWidth(.78f)
            .height(if (isPrimary) 64.dp else 56.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(18.dp),
                ambientColor = gradient.dark.copy(.45f),
                spotColor = gradient.dark.copy(.45f)
            )
            .clip(RoundedCornerShape(18.dp))
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
                RoundedCornerShape(18.dp)
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
        Text(
            text = text,
            color = gradient.border,
            fontWeight = FontWeight.Bold,
            fontSize = if (isPrimary) 24.sp else 20.sp
        )
    }
}