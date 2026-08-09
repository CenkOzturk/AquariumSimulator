package com.kroq.myaquariumsimulator.ui.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.managers.ScreenManager
import com.kroq.myaquariumsimulator.managers.TutorialManager
import com.kroq.myaquariumsimulator.utils.Utils.toDp

@Composable
fun TutorialSpotlight() {
    val bounds = TutorialManager.currentBounds

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Welcome ekranı
        if (bounds == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = .55f))
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) { }
            )

            return@Box
        }

        val blockerModifier = Modifier.clickable(
            indication = null,
            interactionSource = remember {
                MutableInteractionSource()
            }
        ) { }

        // TOP
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(bounds.top.toDp())
                .background(Color.Black.copy(.55f))
                .then(blockerModifier)
        )

        // LEFT
        Box(
            modifier = Modifier
                .offset(
                    x = 0.dp,
                    y = bounds.top.toDp()
                )
                .width(bounds.left.toDp() - 10.dp)
                .height(bounds.height.toDp())
                .background(Color.Black.copy(.55f))
                .then(blockerModifier)
        )

        // RIGHT
        Box(
            modifier = Modifier
                .offset(
                    x = bounds.right.toDp(),
                    y = bounds.top.toDp()
                )
                .width(
                    ScreenManager.screenWidth.dp - bounds.right.toDp() - 10.dp
                )
                .height(bounds.height.toDp())
                .background(Color.Black.copy(.55f))
                .then(blockerModifier)
        )

        // BOTTOM
        Box(
            modifier = Modifier
                .offset(
                    y = bounds.bottom.toDp()
                )
                .fillMaxWidth()
                .height(
                    ScreenManager.screenHeight.dp - bounds.bottom.toDp()
                )
                .background(Color.Black.copy(.55f))
                .then(blockerModifier)
        )

        // Spotlight Border
        Box(
            modifier = Modifier
                .offset(
                    x = bounds.left.toDp(),
                    y = bounds.top.toDp()
                )
                .size(
                    width = bounds.width.toDp(),
                    height = bounds.height.toDp()
                )
        )
    }
}
