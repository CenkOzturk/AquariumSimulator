package com.kukurodev.mykukuroaquarium.ui.aquarium

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import kotlin.math.roundToInt

@Composable
fun GoldFishView(
    x: Float,
    y: Float,
    onClick: () -> Unit
) {

    Image(
        painter = painterResource(R.drawable.gold_fish),
        contentDescription = "Gold Fish",
        modifier = Modifier
            .offset {
                IntOffset(
                    x.roundToInt(),
                    y.roundToInt()
                )
            }
            .size(72.dp)
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                onClick = onClick
            )
    )
}