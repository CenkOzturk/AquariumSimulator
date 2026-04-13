package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.kroq.myaquariumsimulator.game.FishManager
import kotlinx.coroutines.delay

@Composable
fun GameScreen() {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.toFloat()
    val screenHeight = configuration.screenHeightDp.toFloat()

    // 🟦 AQUARIUM BOUNDS (BURASI DOĞRU YER)
    val aquariumWidth = screenWidth * 0.40f
    val aquariumHeight = screenHeight * 0.40f

    val offsetX = (screenWidth - aquariumWidth) / 2f
    val offsetY = (screenHeight - aquariumHeight) / 2f

    LaunchedEffect(Unit) {
        FishManager.init()

        while (true) {
            FishManager.update(aquariumWidth, aquariumHeight)
            delay(16)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Background()

        Aquarium(
            offsetX = offsetX,
            offsetY = offsetY,
            width = aquariumWidth,
            height = aquariumHeight
        )
    }
}
