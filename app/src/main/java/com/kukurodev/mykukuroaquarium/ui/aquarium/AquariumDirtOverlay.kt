package com.kukurodev.mykukuroaquarium.ui.aquarium

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.model.extras.DirtSpot
import kotlin.random.Random

@Composable
fun AquariumDirtOverlay(
    dirtLevel: Float
) {
    val dirtCount = when {
        dirtLevel <= 0f -> 0
        dirtLevel < 0.25f -> 5
        dirtLevel < 0.5f -> 10
        dirtLevel < 0.75f -> 16
        else -> 24
    }

    val dirtAlpha = when {
        dirtLevel < 0.25f -> 0.08f
        dirtLevel < 0.5f -> 0.12f
        dirtLevel < 0.75f -> 0.17f
        else -> 0.22f
    }

    val dirtSpots = remember {
        List(24) {
            DirtSpot(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextInt(5, 16)
            )
        }
    }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        dirtSpots
            .take(dirtCount)
            .forEach { dirt ->
                drawCircle(
                    color = Color(0xFF6B5A3A).copy(alpha = dirtAlpha),
                    radius = dirt.size.dp.toPx() / 2f,
                    center = Offset(
                        x = size.width * dirt.x,
                        y = size.height * dirt.y
                    )
                )
            }
    }
}