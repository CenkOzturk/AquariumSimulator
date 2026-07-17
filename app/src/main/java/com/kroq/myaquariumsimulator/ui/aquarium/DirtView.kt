package com.kroq.myaquariumsimulator.ui.aquarium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.managers.DirtManager

@Composable
fun DirtView() {
    DirtManager.particles.forEach { particle ->

        Box(
            modifier = Modifier
                .offset(
                    x = particle.x.dp,
                    y = particle.y.dp
                )
                .size(5.dp)
                .clip(CircleShape)
                .background(Color(0xFF8B5A2B))
        )
    }
}