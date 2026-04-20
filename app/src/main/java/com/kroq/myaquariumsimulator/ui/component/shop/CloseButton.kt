package com.kroq.myaquariumsimulator.ui.component.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CloseButton(modifier: Modifier, onClose: () -> Unit) {

    Box(
        modifier = modifier
            .padding(20.dp)
            .size(50.dp)
            .background(Color.Black, CircleShape)
            .clickable { onClose() },
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 2.dp),
            text = "✕",
            color = Color.White)
    }
}