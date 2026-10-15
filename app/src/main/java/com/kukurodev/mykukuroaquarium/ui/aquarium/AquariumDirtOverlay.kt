package com.kukurodev.mykukuroaquarium.ui.aquarium

import com.kukurodev.mykukuroaquarium.model.extras.DirtLevel
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
    dirtLevel: DirtLevel
) {
    val dirtSpots = remember {
        List(DirtLevel.BLOCKED.overlaySpotCount) {
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
            .take(dirtLevel.overlaySpotCount)
            .forEach { dirt ->
                drawCircle(
                    color = Color(0xFF6B5A3A).copy(
                        alpha = dirtLevel.overlayAlpha
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