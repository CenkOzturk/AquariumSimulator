package com.kukurodev.mykukuroaquarium.ui.aquarium

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.data.Constants.BUBBLE_VALUE
import com.kukurodev.mykukuroaquarium.managers.BubbleManager
import com.kukurodev.mykukuroaquarium.managers.FloatingTextManager
import com.kukurodev.mykukuroaquarium.ui.theme.MyAquariumSimulatorTheme

@Composable
fun BubbleView() {
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
                        BubbleManager.popBubble(bubble.id)
                        FloatingTextManager.spawn(BUBBLE_VALUE, bubble.x, bubble.y)
                    }
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun BubbleLayerPreview() {
    MyAquariumSimulatorTheme {
        BubbleView()
    }
}