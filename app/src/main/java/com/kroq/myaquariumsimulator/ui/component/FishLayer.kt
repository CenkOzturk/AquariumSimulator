package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.runtime.Composable
import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.utils.Utils

@Composable
fun FishLayer() {
    FishManager.fishes.forEach { fish ->
        FishView(fish, true, 0.73f, {
            Utils.showToast(fish.id.toString())
        })
    }
}