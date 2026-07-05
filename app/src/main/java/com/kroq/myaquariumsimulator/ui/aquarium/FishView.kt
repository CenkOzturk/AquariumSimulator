package com.kroq.myaquariumsimulator.ui.aquarium

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.model.fish.FishModel

@Composable
fun FishView(
    fish: FishModel,
    isFed: Boolean,
    feedProgress: Float,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .offset(fish.move.x.dp, fish.move.y.dp)
            .size(70.dp)
    ) {
        Image(
            painter = painterResource(fish.resId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onClick)
                .graphicsLayer {
                    scaleX = fish.move.direction.toFloat()
                }
        )

        // Feed indicator
        if (isFed) {
            CircularProgressIndicator(
                progress = { feedProgress },
                modifier = Modifier
                    .size(14.dp)
                    .align(Alignment.TopEnd),
                strokeWidth = 2.dp,
                color = Color(0xFF4CAF50),
                trackColor = Color.Black.copy(alpha = 0.15f)
            )
        }
    }
}
