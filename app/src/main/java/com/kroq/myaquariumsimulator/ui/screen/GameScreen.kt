package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.game.AquariumManager
import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.ui.component.AquariumView
import com.kroq.myaquariumsimulator.ui.component.Background
import com.kroq.myaquariumsimulator.ui.component.ShopPopup
import com.kroq.myaquariumsimulator.ui.component.shop.ShopButton
import kotlinx.coroutines.delay

@Composable
fun GameScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.toFloat()
    val screenHeight = configuration.screenHeightDp.toFloat()

    var isShopOpen by remember { mutableStateOf(false) }

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

        ShopButton(
            onClick = { isShopOpen = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        )

        if (isShopOpen) {
            ShopPopup(
                onClose = { isShopOpen = false }
            )
        }
    }
}
