package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.ui.theme.MyAquariumSimulatorTheme

@Composable
fun Aquarium(
    modifier: Modifier = Modifier,
    offsetX: Float,
    offsetY: Float,
    width: Float,
    height: Float
) {
    Box(
        modifier = modifier
            .offset(offsetX.dp, offsetY.dp)
            .width(width.dp)
            .height(height.dp)
            .background(Color(0xFF2F8FCE))
    ) {

        FishLayer()
    }
}

@Preview(showBackground = true)
@Composable
fun AquariumPreview() {
    MyAquariumSimulatorTheme {
        //Aquarium()
    }
}