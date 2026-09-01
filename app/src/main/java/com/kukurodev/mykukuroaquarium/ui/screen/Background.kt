package com.kukurodev.mykukuroaquarium.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.ui.theme.MyAquariumSimulatorTheme

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