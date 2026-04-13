package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel

@Composable
fun AquariumView(aquarium: AquariumModel) {
    Box(
        modifier = Modifier
            .offset(aquarium.offsetX.dp, aquarium.offsetY.dp)
            .width(aquarium.width.dp)
            .height(aquarium.height.dp)
            .background(Color(aquarium.color))
            .border(
                width = 2.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
    ) {

        // 🌊 iç katman (hafif gradient overlay hissi)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(0.08f),
                            Color.Transparent,
                            Color.Black.copy(0.1f)
                        )
                    )
                )
        )

        FishLayer()
    }
}