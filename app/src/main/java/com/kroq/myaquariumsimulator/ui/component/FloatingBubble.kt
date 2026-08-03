package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun FloatingBubble() {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val random = remember {

        Random(System.nanoTime())
    }

    val size = remember {

        random.nextInt(18, 42).dp
    }

    val startX = remember {

        random.nextInt(
            0,
            configuration.screenWidthDp
        ).dp
    }

    val duration = remember {

        random.nextInt(7000, 12000)
    }

    val drift = remember {

        random.nextInt(-35, 35).dp
    }

    val infinite = rememberInfiniteTransition(
        label = ""
    )

    val progress by infinite.animateFloat(

        initialValue = 0f,

        targetValue = 1f,

        animationSpec = infiniteRepeatable(

            animation = tween(
                durationMillis = duration,
                easing = LinearEasing
            )

        ),

        label = ""
    )

    val offsetY = screenHeight - screenHeight * progress

    val offsetX = startX + drift * sin(progress * PI).toFloat()

    Box(
        modifier = Modifier
            .graphicsLayer {

                translationX = offsetX.toPx()

                translationY = offsetY.toPx()
            }
            .size(size)
            .border(
                2.dp,
                Color.White.copy(.28f),
                CircleShape
            )
    ) {

        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(size * .22f)
                .clip(CircleShape)
                .background(
                    Color.White.copy(.45f)
                )
        )
    }
}