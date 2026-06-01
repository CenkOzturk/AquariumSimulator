package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.game.CoinManager
import com.kroq.myaquariumsimulator.game.ScreenManager.screenHeight
import com.kroq.myaquariumsimulator.game.ScreenManager.screenWidth
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.ui.theme.MyAquariumSimulatorTheme
import com.kroq.myaquariumsimulator.utils.Utils.hasItem

@Composable
fun AquariumView(aquarium: AquariumModel) {
    Box(
        modifier = Modifier
            .offset(aquarium.offsetX.dp, aquarium.offsetY.dp)
            .width(aquarium.width.dp)
            .height(aquarium.height.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFF0A2A43))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF3FA7D6),
                            Color(0xFF1B6CA8)
                        )
                    )
                )
        ) {
            CoinManager.texts.forEach { coin ->
                EarnCoinView(coin)
            }

            //SAND
            if (hasItem(300)) {
                Image(
                    painter = painterResource(R.drawable.sand),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
            }

            //SEAWEED
            if (hasItem(302)) {
                Image(
                    painter = painterResource(R.drawable.seaweed_short),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(
                            bottom = 20.dp,
                            start = 10.dp
                        )
                )
            }

            FishLayer()

            //STAR
            if (hasItem(303)) {
                Image(
                    painter = painterResource(R.drawable.starfish),
                    contentDescription = null,
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.TopStart)
                        .padding(start = 20.dp, bottom = 50.dp)
                )
            }

            //ROCKS
            if (hasItem(301)) {
                Image(
                    painter = painterResource(R.drawable.rock_small),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.BottomStart)
                )
                Image(
                    painter = painterResource(R.drawable.rock_small),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.BottomEnd)
                )
            }

            BubbleView()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.White.copy(0.25f),
                            Color.Transparent
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    2.dp,
                    Color.White.copy(alpha = 0.3f),
                    RoundedCornerShape(28.dp)
                )
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(20.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color.White.copy(0.15f),
                            Color.Transparent
                        )
                    )
                )
        )
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
                fishCount = 2,
                price = 100
            )
        )
    }
}