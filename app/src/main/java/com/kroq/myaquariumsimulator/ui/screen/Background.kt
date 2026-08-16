package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.ui.theme.MyAquariumSimulatorTheme

@Composable
fun Background(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.bg_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .clipToBounds()
    )
}

@Preview(showBackground = true)
@Composable
fun BackgroundPreview() {
    MyAquariumSimulatorTheme {
        Background()
    }
}