package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.game.BubbleManager
import com.kroq.myaquariumsimulator.ui.theme.MyAquariumSimulatorTheme

@Composable
fun BubbleLayer() {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        BubbleManager.bubbles.forEach { bubble ->

            Box(
                modifier = Modifier
                    .offset(bubble.x.dp, bubble.y.dp)
                    .size((bubble.radius * 2).dp)
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(alpha = 0.25f)
                    )
                    .border(
                        2.dp,
                        Color.White.copy(alpha = 0.5f),
                        CircleShape
                    )
                    .clickable {
                        BubbleManager.popBubble(context, bubble)
                    }
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun BubbleLayerPreview() {
    MyAquariumSimulatorTheme {
        BubbleLayer()
    }
}