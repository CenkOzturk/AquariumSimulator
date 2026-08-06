package com.kroq.myaquariumsimulator.ui.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.managers.ScreenManager
import com.kroq.myaquariumsimulator.managers.TutorialManager
import com.kroq.myaquariumsimulator.utils.Utils.toDp

@Composable
fun TutorialSpotlight() {
    val bounds = TutorialManager.currentBounds ?: return

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // TOP

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(bounds.top.toDp())
                .background(Color.Black.copy(.55f))
        )

        // LEFT

        Box(
            modifier = Modifier
                .offset(
                    x = 0.dp,
                    y = bounds.top.toDp()
                )
                .width(bounds.left.toDp())
                .height(bounds.height.toDp())
                .background(Color.Black.copy(.55f))
        )

        // RIGHT

        Box(
            modifier = Modifier
                .offset(
                    x = bounds.right.toDp(),
                    y = bounds.top.toDp()
                )
                .fillMaxWidth()
                .width(
                    ScreenManager.screenWidth.dp - bounds.right.toDp()
                )
                .height(bounds.height.toDp())
                .background(Color.Black.copy(.55f))
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
                .fillMaxHeight()
                .background(Color.Black.copy(.55f))
        )
    }
}
