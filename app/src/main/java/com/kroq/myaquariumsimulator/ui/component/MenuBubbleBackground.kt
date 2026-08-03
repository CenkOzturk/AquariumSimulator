package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MenuBubbleBackground() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        repeat(5) {
            FloatingBubble()
        }
    }
}