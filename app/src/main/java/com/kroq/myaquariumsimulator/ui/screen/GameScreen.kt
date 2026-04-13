package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.kroq.myaquariumsimulator.game.AquariumManager
import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.ui.component.AquariumView
import com.kroq.myaquariumsimulator.ui.component.Background
import kotlinx.coroutines.delay

@Composable
fun GameScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.toFloat()
    val screenHeight = configuration.screenHeightDp.toFloat()

    AquariumManager.init(screenWidth, screenHeight)

    LaunchedEffect(screenWidth, screenHeight) {
        FishManager.init()

        AquariumManager.currentAquarium?.let { currentAquarium ->
            while (true) {
                FishManager.update(
                    currentAquarium.width,
                    currentAquarium.height
                )
                delay(16)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Background()

        AquariumManager.currentAquarium?.let {
            AquariumView(it)
        }
    }
}
