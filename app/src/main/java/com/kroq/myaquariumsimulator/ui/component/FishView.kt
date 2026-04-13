package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.model.fish.FishModel

@Composable
fun FishUi(fish: FishModel) {
    Image(
        painter = painterResource(fish.resId),
        contentDescription = null,
        modifier = Modifier
            .offset(fish.x.dp, fish.y.dp)
            .size(70.dp)
            .graphicsLayer {
                scaleX = fish.direction.toFloat()
            }
    )
}