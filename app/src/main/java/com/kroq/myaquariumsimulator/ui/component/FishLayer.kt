package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.runtime.Composable
import com.kroq.myaquariumsimulator.game.FishManager

@Composable
fun FishLayer() {
    FishManager.fishes.forEach { fish ->
        FishView(fish)
    }
}