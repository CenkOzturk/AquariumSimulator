package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.game.ScreenManager.screenHeight
import com.kroq.myaquariumsimulator.game.ScreenManager.screenWidth
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.ui.theme.MyAquariumSimulatorTheme

@Composable
fun AquariumView(aquarium: AquariumModel) {
    Box(
        modifier = Modifier
            .offset(aquarium.offsetX.dp, aquarium.offsetY.dp)
            .width(aquarium.width.dp)
            .height(aquarium.height.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0x663BAED6), // üst daha açık
                        Color(0xFF1D6FA5)  // alt koyu
                    )
                )
            )
            .border(
                2.dp,
                Color.White.copy(alpha = 0.2f),
                RoundedCornerShape(24.dp)
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
        BubbleLayer()

        FishLayer()
    }
}

@Preview(showBackground = true)
@Composable
fun AquariumViewPreview() {
    MyAquariumSimulatorTheme {
        AquariumView(
            AquariumModel(
                type = AquariumType.SMALL,
                offsetX = screenWidth * 0.2f,
                offsetY = screenHeight * 0.2f,
                width = screenWidth * 0.6f,
                height = screenHeight * 0.35f,
                color = 0xFF3A86FF,
                fishCount = 2
            )
        )
    }
}