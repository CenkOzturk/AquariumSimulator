package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.runtime.Composable
import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.ui.component.FishUi

@Composable
fun FishLayer() {
    FishManager.fishes.forEach { fish ->
        FishUi(fish)
    }
}