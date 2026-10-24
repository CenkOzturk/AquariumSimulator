package com.kukurodev.mykukuroaquarium.ui.tutorial

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.managers.ScreenManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.utils.Utils.toDp

@Composable
fun TutorialSpotlight() {
    val bounds = TutorialManager.currentBounds

    val blockerModifier = Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) { }

    val infiniteTransition = rememberInfiniteTransition(label = "SpotlightPulsing")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.10f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseScale"
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (bounds == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { }
            )
            return@Box
        }

        val centerXDp = bounds.center.x.toDp()
        val centerYDp = bounds.center.y.toDp()
        val radiusDp = (maxOf(bounds.width, bounds.height) / 2f).toDp() + 10.dp
        val dimColor = Color.Black.copy(alpha = 0.62f)

        val topDp = (centerYDp - radiusDp).coerceAtLeast(0.dp)
        val bottomDp = (centerYDp + radiusDp).coerceAtMost(ScreenManager.screenHeight.dp)
        val leftDp = (centerXDp - radiusDp).coerceAtLeast(0.dp)
        val rightDp = (centerXDp + radiusDp).coerceAtMost(ScreenManager.screenWidth.dp)
        val heightDp = (bottomDp - topDp).coerceAtLeast(0.dp)

        // 1. VISUAL: 100% True Smooth Circular Cutout Canvas (NO pointerInput or clickable modifier attached, so Compose hit-testing completely ignores this drawing layer!)
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = 0.99f }
        ) {
            drawRect(dimColor)

            val radiusPx = (maxOf(bounds.width, bounds.height) / 2f) + 10.dp.toPx()
            drawCircle(
                color = Color.Transparent,
                radius = radiusPx,
                center = Offset(bounds.center.x, bounds.center.y),
                blendMode = BlendMode.Clear
            )
        }

        // 2. TOUCH BLOCKERS: Placed ONLY outside the circular aperture (top, bottom, left, right)!
        // Target area inside radiusDp has NO blocker box, so taps pass directly to underlying buttons/fishes/coins!
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(topDp)
                .then(blockerModifier)
        )
        Box(
            modifier = Modifier
                .offset(x = 0.dp, y = bottomDp)
                .fillMaxWidth()
                .height((ScreenManager.screenHeight.dp - bottomDp).coerceAtLeast(0.dp))
                .then(blockerModifier)
        )
        Box(
            modifier = Modifier
                .offset(x = 0.dp, y = topDp)
                .width(leftDp)
                .height(heightDp)
                .then(blockerModifier)
        )
        Box(
            modifier = Modifier
                .offset(x = rightDp, y = topDp)
                .width((ScreenManager.screenWidth.dp - rightDp).coerceAtLeast(0.dp))
                .height(heightDp)
                .then(blockerModifier)
        )

        // 3. Animated Pulsing Glowing Golden Ring around Target
        val animatedDiameter = (radiusDp * 2f + 4.dp) * pulseScale
        Box(
            modifier = Modifier
                .offset(
                    x = centerXDp - (animatedDiameter / 2f),
                    y = centerYDp - (animatedDiameter / 2f)
                )
                .size(animatedDiameter)
                .border(
                    width = 3.dp,
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0xFFFFF176),
                            Color(0xFFFFB300),
                            Color(0xFFFF8F00)
                        )
                    ),
                    shape = CircleShape
                )
        )
    }
}
