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
    dirtLevel: Int
) {
    val dirtCount = when {
        dirtLevel <= 0 -> 0
        dirtLevel <= 25 -> 20
        dirtLevel <= 50 -> 40
        dirtLevel <= 75 -> 80
        else -> 150
    }

    val dirtAlpha = when {
        dirtLevel <= 25 -> 0.16f
        dirtLevel <= 50 -> 0.24f
        dirtLevel <= 75 -> 0.32f
        else -> 0.42f
    }

    val dirtSpots = remember {
        List(45) {
            DirtSpot(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextInt(5, 40)
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
                    color = Color(0xFF6B5A3A).copy(
                        alpha = dirtAlpha
                    ),
                    radius = dirt.size.dp.toPx() / 2f,
                    center = Offset(
                        x = size.width * dirt.x,
                        y = size.height * dirt.y
                    )
                )
            }
    }
}