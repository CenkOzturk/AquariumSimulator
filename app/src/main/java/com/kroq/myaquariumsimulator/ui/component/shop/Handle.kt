package com.kroq.myaquariumsimulator.ui.component.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Handle(modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(top = 10.dp)
            .width(40.dp)
            .height(5.dp)
            .background(Color.Gray, RoundedCornerShape(50))
    )
}